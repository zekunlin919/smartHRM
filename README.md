# SmartHRM

SmartHRM 是一个基于 Spring Boot 的人力资源管理系统，旨在帮助企业高效管理其员工、技能、项目及培训等信息。

## 多语言目录 Multi-Language Index
- README-cn.md - Here
- README-en.md - [README-en.md](https://github.com/Murasame-Chyan/smartHRM/blob/master/README-en.md)。

## 功能概览

- **员工管理**：支持员工的增删改查操作。
- **技能匹配**：根据项目需求技能，智能匹配合适的员工。
- **项目检索**：根据项目名字段或参与员工id模糊搜索项目。
- **任务分配**：对项目成员分配任务，统计完成率。
- **部门与项目管理**：管理组织架构和项目信息。
- **培训管理**：管理员工培训记录。

## 技术栈

- Spring Boot
- MongoDB
- Thymeleaf (前端模板识别)
- Bootstrap & jQuery (样式表 + 数据交互 & http请求)

## 模块说明

### 核心模块

- **EmployeeController**：员工信息管理，员工的基本CRUD。
- **SkillMatchController**：技能匹配功能，支持根据项目所需技能搜索合适员工。
- **ProjectMatchController**：项目&任务信息管理，基本CRUD进行项目增设、任务分配、项目进度统计。
- **SkillController**：技能信息管理。
- **TrainingController**：培训信息管理，训练新增。
- **DepartmentController**：部门管理，可以进行员工迁移。

### 数据访问层

- **Dao**：直接访问MongoTemplate的CRUD操作和各类查询（模糊、分页查询）。
- **Repo**：实体类数据访问层。
- **DTO**：简易数据传输对象，放置小型数据传输对象减少冗余数据传输量，安置数据传输方法和特殊处理方法。

### 实体类

- **Employee**：员工实体，包含姓名、所属部门、技能列表等信息。
- **Skill**：技能实体，包含技能名称、类型等信息。
- **Project**：项目实体，包含项目名称、成员列表、所需技能等信息。
- **Department**：部门实体，包含部门名称、经理ID、员工列表等信息。
- **Task**：项目内多名员工分配任务。
- **Training**：指定多名员工培训课程。

## 快速开始

### 环境要求

- Java 17
- MongoDB
- Maven
- SpringBoot 3.x

### 配置

1. 修改 `application.yml` 或使用 `application-dev.yml` / `application-prod.yml` 配置数据库连接等信息。
2. 启动 MongoDB 服务。

### 启动项目

	1. 运行主类 `SmartHrmApplication.java` 
	1. 运行最新版本 `.jar` 包

```bash
java -jar xxx.jar
```

访问 `http://localhost:8080/对应路由/` 使用系统。

## 使用说明

- **员工管理**：访问 `/employees/` 路由，可进行员工的添加、修改、删除操作。
- **项目管理**：访问 `/departments/` 路由，可进行项目的添加、修改、删除操作。
- **技能匹配**：访问 `/skillmatch/` 路由，选择所需技能后，系统将自动匹配符合条件的员工。
- **技能与训练管理**：访问 `/training/` 路由，管理技能与训练信息。
- **项目匹配管理**：访问 `/projectmatch/` 路由，以项目名或参与员工id模糊搜索项目详情。
- **application.yml**：设置本地数据库或云端数据库url，详见生效yml文件。

## 贡献指南

欢迎贡献代码或提出建议！请遵循以下步骤：

1. Fork 本仓库。
2. 创建新分支 (`git checkout -b feature/new-feature`)。
3. 从远程仓库-主分支获取最新更新后将新增文件加入git版本控制。
4. 提交更改与更改描述 (`git commit -m 'Add new feature'`)。
5. 推送至远程分支 (`git push origin feature/new-feature`)。
6. 提交 Pull Request。

## 许可证

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 初版演示（未样式统一）

### 	**员工管理**： `/employees/` 

#### 	总界面

<img src="pics\employee.png"  />

#### 	编辑员工

<img src="pics\modEmployee.png"  />

#### 	新增员工

<img src="pics\addEmployee.png"  />

### 	**部门管理**： `/departments/` 

#### 	总界面

<img src="pics\department.png"  />

#### 	编辑项目

<img src="pics\modDepartment.png"  />

#### 	增设部门

<img src="pics\addDepartment.png"  />

### 	**技能匹配**： `/skillmatch/` 

<img src="pics\skillMatch.png"  />

### 	**项目管理**： `/projectmatch/`

#### 	总界面

<img src="pics\project1.png"  />

#### 	编辑项目

<img src="pics\modProject.png"  />

#### 	分配任务

<img src="pics\task.png"  />

#### 	启动项目

<img src="pics\addProject.png" style="zoom:67%;" />

### 	**技能与训练管理**： `/training/`

<img src="pics\skill&training.png"  />
