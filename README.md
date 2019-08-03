# EPPDEV-MLIB-POM

## 说明

本项目是一个机器学习模型落地的计算框架，通过mlib-register、mlib-provider、mlib-consumer、mlib-monitor相互配合，
实现模型的发布、外部接口调用和计算功能

本项目主要支持通过pmml方式进行模型的部署，适用于大量的模型需要部署的场景，主要支持的模型为经典机器学习计算模型，
具体包括：

* 逻辑回归
* XGBoost
* 决策树
* 支持向量机SVM
* ……

具体的算法依赖于[jpmml](github.com/jpmml)，故支持的模型一jpmml官方清单为准


## eppdev-mlib-register

### 主要功能

#### 服务注册与发现功能

服务接口功能主要实现相关服务的注册与发现功能，具体提供的接口包括：

1. provider的注册功能，支持executor启动后向register的注册
2. 服务发现接口，支持consumer根据模型id查找已经部署了该模型的provider
3. 心跳监检测功能，定期检测各个provider、consumer的心跳信息，确认其状态

#### 模型管理功能

主要实现：

1. 模型的发布，通过web管理界面或者HttpRest接口实现模型的发布功能
2. 模型的复制、迁移，通过web管理界面将模型额外部署到另外的provider中，或者迁移到另外的provider中
2. 模型发布的再平衡，自动生成迁移或者再平衡的计算规则，实现模型的再平衡【非开源版本功能】

> 其中模型的再平衡功能前期主要考虑实现手工的调整

#### 模型执行监控功能

主要实现的功能包括：

1. 定期获取各个provider各个阶段模型的调用情况
2. 将模型调用情况持久化到数据库中

#### 主备register的配置信息同步功能

主要实现的功能包括：

1. 配置信息的同步
2. 主register停止服务后，备用register的接管

### 主要实使用的技术栈和外部组件

1. MySQL: 数据持久化
2. MyBatis：OR映射
3. springboot/springmvc
4. themeleaf
5. httpclient
6. redis: 可选，静态数据的缓存
7. eppdev-jee：使用单不依赖，用于生成OR映射代码


## eppdev-mlib-provider

### 主要功能

#### 服务注册

1. 启动后自动向主备register进行注册
2. 定期发送心跳信息到register
3. 提供心跳监测接口，完成心跳监测
4. 提供模型查询接口，获取已部署的模型列表

#### 模型发布与执行功能

1. 模型发布接口，通过HttpRest接口，实现模型的发布功能
2. 模型的执行接口，通过HttpRest接口，实现模型的计算，返回模型计算结果
3. 提供模型卸载接口，根据需要实现模型的卸载

#### 模型计算情况的统计

1. 定期统计模型调用情况，并将其汇总送mlib-monitor，完成日志的统计和计算

### 主要技术栈和外部组件

1. spring/springboot
2. httpclient

## eppdev-mlib-monitor

### 主要功能

#### 注册功能

主要提供：

1. 启动后自动向register进行注册
2. 定期发送心跳信息到register
3. 提供心跳监测功能

#### 日志监控管理功能

mlib-monitor主要提供日志监控功能，主要包括：

1. 提供Http Rest接口，接收provider定期发送的监控日志
2. 提供http Rest接口，供register调用，支持监控日志的查询功能

### 主要技术栈

1. spring/springboot
2. MySQL
3. Mybatis
4. Redis: 可选，缓存

## eppdev-mlib-consumer

### 主要功能

#### 服务注册功能

1. 启动后向register进行服务注册
2. 定期发送心跳请求到register
3. 提供心跳监测接口，供register调用
4. 缓存modelId对应的provider信息
5. 提供provider更新接口，定期实现executor信息的更新

#### 接口调用功能

1. 根据模型id自动调用相应的provider，完成模型的计算
2. 调用provider之前首先通过调用executor的心跳以及模型id的检测接口，用于进行接口的判断，若心跳失败或者id检测接口失效，则向register请求最新的接口

### 主要技术栈

1. spring/springboot
2. httpclient



## 许可

版权所有： 2019， [郝金隆](mailto:jinlong.hao@eppdev.cn)
软件许可： [996 License v1.0](LICENSE-CN)