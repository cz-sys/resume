package cn.edu.haust.resumebackend.service.impl;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.session.SaSession;
import cn.edu.haust.resumebackend.domain.entity.SaTokenMysqlData;
import cn.edu.haust.resumebackend.mapper.SaTokenMysqlMapper;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chengzhen
 * created_at 2025/5/2
 */
@Service
public class SaTokenMysqlDaoImpl implements SaTokenDao {
    @Resource
    private SaTokenMysqlMapper saTokenMysqlMapper;

    LocalDateTime getExpireAtFromTimeout(long timeout) {
        return timeout == SaTokenDao.NEVER_EXPIRE ? null : LocalDateTime.now().plusSeconds(timeout);
    }

    @Override
    public String get(String key) {
        return Optional.ofNullable(saTokenMysqlMapper.selectOne(new LambdaQueryWrapper<SaTokenMysqlData>().eq(SaTokenMysqlData::getTokenKey, key))).map(SaTokenMysqlData::getString).orElse(null);
    }

    @Override
    public void set(String key, String value, long timeout) {
        if (timeout == 0 || timeout <= SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }
        SaTokenMysqlData saTokenMysqlData = saTokenMysqlMapper.selectOne(new LambdaQueryWrapper<SaTokenMysqlData>().eq(SaTokenMysqlData::getTokenKey, key));
        if (ObjectUtil.isEmpty(saTokenMysqlData)) {
            saTokenMysqlData = SaTokenMysqlData.builder()
                    .tokenKey(key)
                    .expireAt(getExpireAtFromTimeout(timeout))
                    .string(value)
                    .build();
        } else {
            saTokenMysqlData.setExpireAt(getExpireAtFromTimeout(timeout));
            saTokenMysqlData.setString(value);
        }
        saTokenMysqlMapper.insertOrUpdate(saTokenMysqlData);
    }

    @Override
    public void update(String key, String value) {
        long expire = getTimeout(key);
        // -2 = 无此键
        if (expire == SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }
        this.set(key, value, expire);
    }

    @Override
    public void delete(String key) {
        saTokenMysqlMapper.delete(new LambdaQueryWrapper<SaTokenMysqlData>().eq(SaTokenMysqlData::getTokenKey, key));
    }

    @Override
    public long getTimeout(String key) {
        LocalDateTime localDateTime = Optional.ofNullable(saTokenMysqlMapper
                        .selectOne(new LambdaQueryWrapper<SaTokenMysqlData>().eq(SaTokenMysqlData::getTokenKey, key)))
                .map(SaTokenMysqlData::getExpireAt).orElse(LocalDateTime.MIN);

        long seconds = Duration.between(LocalDateTime.now(), localDateTime).getSeconds();
        if (seconds < 0) {
            return 0;
        }
        return seconds;
    }

    @Override
    public void updateTimeout(String key, long timeout) {
        // 判断是否想要设置为永久
        if (timeout == SaTokenDao.NEVER_EXPIRE) {
            long expire = getTimeout(key);
            //noinspection StatementWithEmptyBody
            if (expire == SaTokenDao.NEVER_EXPIRE) {
                // 如果其已经被设置为永久，则不作任何处理
            } else {
                // 如果尚未被设置为永久，那么再次set一次
                this.set(key, this.get(key), timeout);
            }
            return;
        }

        SaTokenMysqlData saTokenMysqlData = saTokenMysqlMapper
                .selectOne(new LambdaQueryWrapper<SaTokenMysqlData>().eq(SaTokenMysqlData::getTokenKey, key));
        if (ObjectUtil.isEmpty(saTokenMysqlData)) {
            saTokenMysqlData = SaTokenMysqlData.builder()
                    .expireAt(getExpireAtFromTimeout(timeout))
                    .build();
        } else {
            saTokenMysqlData.setExpireAt(getExpireAtFromTimeout(timeout));
        }
        saTokenMysqlMapper.insertOrUpdate(saTokenMysqlData);
    }

    @Override
    public Object getObject(String key) {
        return Optional.ofNullable(saTokenMysqlMapper
                        .selectOne(new LambdaQueryWrapper<SaTokenMysqlData>().eq(SaTokenMysqlData::getTokenKey, key)))
                .map(item -> JSONUtil.toBean(item.getString(), SaSession.class)).orElse(null);
    }

    @Override
    public <T> T getObject(String key, Class<T> aClass) {
        return aClass.cast(getObject(key));
    }

    @Override
    public void setObject(String key, Object object, long timeout) {
        if (timeout == 0 || timeout <= SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }

        SaTokenMysqlData saTokenMysqlData = saTokenMysqlMapper
                .selectOne(new LambdaQueryWrapper<SaTokenMysqlData>().eq(SaTokenMysqlData::getTokenKey, key));
        if (ObjectUtil.isEmpty(saTokenMysqlData)) {
            saTokenMysqlData = SaTokenMysqlData.builder()
                    .tokenKey(key)
                    .expireAt(getExpireAtFromTimeout(timeout))
                    .string(JSONUtil.toJsonStr(object))
                    .build();
        } else {
            saTokenMysqlData.setExpireAt(getExpireAtFromTimeout(timeout));
            saTokenMysqlData.setString(JSONUtil.toJsonStr(object));
        }
        saTokenMysqlMapper.insertOrUpdate(saTokenMysqlData);
    }

    @Override
    public void updateObject(String key, Object object) {
        long expire = getObjectTimeout(key);
        // -2 = 无此键
        if (expire == SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }
        this.setObject(key, object, expire);
    }

    @Override
    public void deleteObject(String key) {
        delete(key);
    }

    @Override
    public long getObjectTimeout(String key) {
        return getTimeout(key);
    }

    @Override
    public void updateObjectTimeout(String key, long timeout) {
        updateTimeout(key, timeout);
    }

    @Override
    public SaSession getSession(String sessionId) {
        return (SaSession) getObject(sessionId);
    }

    @Override
    public void setSession(SaSession session, long timeout) {
        setObject(session.getId(), session, timeout);
    }

    @Override
    public void updateSession(SaSession saSession) {
        updateObject(saSession.getId(), saSession);
    }

    @Override
    public void deleteSession(String sessionId) {
        deleteObject(sessionId);
    }

    @Override
    public long getSessionTimeout(String sessionId) {
        return getObjectTimeout(sessionId);
    }

    @Override
    public void updateSessionTimeout(String sessionId, long timeout) {
        updateObjectTimeout(sessionId, timeout);
    }

    @Override
    public List<String> searchData(String prefix, String keyword, int start, int size, boolean sortType) {

        LambdaQueryWrapper<SaTokenMysqlData> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.hasText(prefix)) {
            queryWrapper.likeRight(SaTokenMysqlData::getTokenKey, prefix);
        }
        if (StringUtils.hasText(keyword)) {
            queryWrapper.like(SaTokenMysqlData::getTokenKey, keyword);
        }
        Page<SaTokenMysqlData> page = new Page<>(start, size);
        Page<SaTokenMysqlData> pageResult = saTokenMysqlMapper.selectPage(page, queryWrapper);
        return pageResult.getRecords().stream()
                .map(SaTokenMysqlData::getTokenKey)
                .collect(Collectors.toList());
    }
}
