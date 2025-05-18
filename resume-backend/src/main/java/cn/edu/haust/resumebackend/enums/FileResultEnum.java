package cn.edu.haust.resumebackend.enums;

import cn.edu.haust.resumebackend.utils.ResultCodeWithMsg;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chengzhen
 * created_at 2025/5/10
 */
@Getter
@AllArgsConstructor
public enum FileResultEnum implements ResultCodeWithMsg {
    UPLOAD_SUCCESS("FILE-200", "上传成功"),
    FILE_NAME_NOT_EXIST("FILE-400", "文件名为空"),
    MODULE_IS_EMPTY("FILE-404", "模块名为空"),
    UPLOAD_FAIL("FILE-500", "上传失败"),
    PERSIST_FAIL("FILE-501", "文件持久化失败");
    private final String code;
    private final String msg;
}
