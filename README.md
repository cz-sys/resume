# 全面简历支持平台
在当今竞争激烈的就业市场中，一份出色的简历是打开职业成功大门的钥匙。我们的全面简历支持平台致力于为求职者提供全方位的简历优化服务，帮助你在众多候选人中脱颖而出。

# 快速开始

以下是快速启动和运行本项目的步骤。按照以下指南，你可以在本地环境中轻松部署前端和后端项目。

## 环境准备

在开始之前，请确保你的系统已经安装了以下工具：

- [Git](https://git-scm.com/)
- [Node.js](https://nodejs.org/)（前端项目需要）
- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)（后端项目需要）
- [Maven](https://maven.apache.org/)（后端项目需要）

### 注意

- 如果需要使用数据库服务，请确保[Mysql8.0](https://www.mysql.com/)服务已经启动
- 创建一个resume数据库，执行resume/sql当中的数据库脚本文件
- 默认连接的数据库为resume数据库
- 默认配置为username: root, password: 123456
- 如果配置不同，请同步修改resume/resume-backend/src/main/resources目录下application-prod.yml文件当中的数据库配置

## 克隆项目

首先，克隆项目到本地：

```bash
git clone https://github.com/cz-sys/resume.git
```

## 启动后端服务

### 进入后端项目目录

进入后端项目的目录：

```bash
cd ./resume/resume-backend
```

### 安装依赖并启动

运行以下命令安装后端项目依赖并启动服务：

```bash
mvn clean install
mvn spring-boot:run
```

如果一切正常，后端服务将启动，默认情况下监听 `8080` 端口。你可以通过访问 `http://localhost:8080` 来验证后端服务是否正常运行（如果后端有相应的测试接口）。

### 注意事项

- 如果你的系统中已安装了其他服务占用了 `8080` 端口，可以通过修改 `application.properties` 或 `application.yml` 文件中的 `server.port` 配置项来更改端口。
- 如果需要使用数据库，确保你的数据库服务已启动，并根据 `application.properties` 或 `application.yml` 文件中的配置连接到正确的数据库。
- 该服务是前台启动，启动前端服务请重新在resume-frontend目录下重新打开一个命令行窗口，执行接下来的命令

## 启动前端服务

### 进入前端项目目录

进入前端项目的目录：

```bash
cd ../resume-frontend
```

### 安装依赖

运行以下命令安装前端项目依赖：

```bash
npm install
```

### 启动开发服务器

安装完成后，启动前端开发服务器：

```bash
npm run dev
```

启动成功后，通常会在浏览器中自动打开项目地址（通常是 `http://localhost:5173` 或其他默认端口）。如果未自动打开，你可以手动在浏览器中输入该地址查看项目运行情况。

## 验证项目运行

- 确保后端服务和前端服务均已成功启动。
- 打开浏览器，访问前端项目的地址（如 `http://localhost:5173`），并尝试与后端服务进行交互（如提交表单、获取数据等），以验证前后端是否正常通信。

启动成功后，通常会在浏览器中自动打开项目地址（通常是 `http://localhost:5173` 或其他默认端口）。如果未自动打开，你可以手动在浏览器中输入该地址查看项目运行情况。

## 注意事项

- 确保你已经安装了 [Node.js](https://nodejs.org/) 和 [npm](https://www.npmjs.com/)。
- 如果在安装依赖或启动过程中遇到问题，请检查你的网络连接，或者尝试使用其他 npm 镜像源（如淘宝镜像）。
- 如果需要配置其他环境变量，请参考项目的配置文件或文档。