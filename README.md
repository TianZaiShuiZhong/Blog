# ZXH Personal Space (个人空间)这是一个基于 **Spring Boot 3** 和 **Vue 3** 构建的现代化个人博客与展示网站。

### 2. 启动后端服务
```bash
cd backend
mvn spring-boot:run
```
> 后端服务默认运行在端口 `8080`。
> H2 控制台地址: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:blogdb`)

修改文章图片内容在http://localhost:5173/admin

### 3. 启动前端服务
打开一个新的终端窗口：
```bash
cd frontend
npm install  # 初次运行需要安装依赖
npm run dev
```
> 前端服务默认运行在 `http://localhost:5173`。
> 如果端口被占用，Vite 会自动切换到 `5174` 等端口，请留意终端输出。

## 📂 项目结构

```
xxm/
├── backend/                 # Spring Boot 后端源码
│   ├── src/main/java/       # Java 源代码 (Controller, Service, Model)
│   └── src/main/resources/  # 配置文件 (application.properties)
├── frontend/                # Vue 3 前端源码
│   ├── src/views/           # 页面组件 (Home, Blog, Gallery, Admin...)
│   ├── src/router/          # 路由配置
│   └── vite.config.js       # Vite 配置 (包含 API 代理设置)
└── README.md                # 项目说明文档
```

## 👤 作者信息

- **Name**: ZXH
- **Email**: 2427328922@qq.com
- **GitHub**: [TianZaiShuiZhong](https://github.com/TianZaiShuiZhong)
- **Location**: 广东省广州

