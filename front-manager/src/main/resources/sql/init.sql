USE repository;

DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS permission;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS role_permission;
DROP TABLE IF EXISTS file;

#create user table
CREATE TABLE user (
  id INT NOT NULL AUTO_INCREMENT COMMENT '唯一标识ID',
  user_name VARCHAR(300) NOT NULL COMMENT '用户名',
  nick_name VARCHAR(300) NOT NULL COMMENT '昵称或姓名',
  sex TINYINT NOT NULL DEFAULT 0 COMMENT '性别，默认为0，表示男，1表示女',
  phone VARCHAR(100) COMMENT '手机号码',
  email VARCHAR(300) NOT NULL UNIQUE COMMENT '邮箱',
  password VARCHAR(300) NOT NULL COMMENT '密码',
  salt VARCHAR(300) NOT NULL COMMENT '密码盐,用于对密码进行加密',
  locked SMALLINT DEFAULT 0 COMMENT '状态,-1:表示注册了，但是没验证,0:表示禁用,1:表示正常',
  birthday DATETIME NOT NULL DEFAULT now() COMMENT '出生日期',
  create_date DATETIME NOT NULL DEFAULT now() COMMENT '创建时间',
  modify_date DATETIME NOT NULL DEFAULT now() ON UPDATE now() COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE INDEX (user_name),
  UNIQUE INDEX (phone),
  UNIQUE INDEX (email)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

#create role table
CREATE TABLE role (
  id INT NOT NULL AUTO_INCREMENT COMMENT '角色唯一标识ID',
  name VARCHAR(200) NOT NULL COMMENT '角色名称',
  description VARCHAR(300) COMMENT '角色描述',
  available SMALLINT DEFAULT 0 COMMENT '是否可用，0:表示不可用，1:表示可用',
  create_date DATETIME NOT NULL DEFAULT now() COMMENT '创建时间',
  modify_date DATETIME NOT NULL DEFAULT now() ON UPDATE now() COMMENT '更新时间',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

#create permission table
CREATE TABLE permission (
  id INT NOT NULL AUTO_INCREMENT COMMENT '资源唯一标识ID',
  parent_id INT COMMENT '父级菜单Id',
  parent_ids VARCHAR(300) COMMENT '父级编号列表',
  name VARCHAR(200) NOT NULL COMMENT '资源名称',
  icon VARCHAR(100) DEFAULT '' COMMENT '图标',
  type INT COMMENT '资源类型，1表示菜单(menu)，2表示连接地址(url)，3表示按钮(button)',
  url VARCHAR(300) COMMENT '资源路径',
  permission VARCHAR(500) COMMENT '权限字符串,如,user,role:*,按钮,button:create..',
  available SMALLINT DEFAULT 1 COMMENT '是否可用,默认为1,0:禁用，1:表示可用',
  sort INT DEFAULT 1 COMMENT '权限资源排序',
  create_date DATETIME NOT NULL DEFAULT now() COMMENT '创建时间',
  modify_date DATETIME NOT NULL DEFAULT now() ON UPDATE now() COMMENT '更新时间',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

#create user_role table
CREATE TABLE user_role (
  id INT NOT NULL AUTO_INCREMENT COMMENT '唯一标识ID',
  user_id INT NOT NULL COMMENT '用户唯一标识ID',
  role_id INT NOT NULL COMMENT '角色唯一标识ID',
  description VARCHAR(500) COMMENT '描述',
  create_date DATETIME NOT NULL DEFAULT now() COMMENT '创建时间',
  modify_date DATETIME NOT NULL DEFAULT now() ON UPDATE now() COMMENT '更新时间',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

#create role_permission table
CREATE TABLE role_permission (
  id INT NOT NULL AUTO_INCREMENT COMMENT '唯一标识ID',
  role_id INT NOT NULL COMMENT '角色唯一标识ID',
  permission_id INT NOT NULL COMMENT '权限唯一标识ID',
  description VARCHAR(500) COMMENT '描述',
  create_date DATETIME NOT NULL DEFAULT now() COMMENT '创建时间',
  modify_date DATETIME NOT NULL DEFAULT now() ON UPDATE now() COMMENT '更新时间',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

#create file manager table
CREATE TABLE file (
  id INT NOT NULL AUTO_INCREMENT COMMENT '文件资源唯一表示ID',
  role_id INT NOT NULL COMMENT '文件所属者ID',
  origin_name VARCHAR(300) NOT NULL COMMENT '上传文件原始名称',
  new_name VARCHAR(300) NOT NULL COMMENT '新文件名，用于存放在服务端用',
  extension VARCHAR(100) COMMENT '文件后缀',
  type VARCHAR(200) COMMENT '文件类型',
  size BIGINT DEFAULT 0 COMMENT '文件大小',
  save_path VARCHAR(500) NOT NULL COMMENT '上传文件存放路径',
  upload_date DATETIME NOT NULL DEFAULT now() COMMENT '文件上传日期',
  modify_date DATETIME NOT NULL DEFAULT now() ON UPDATE now() COMMENT '文件修改日期',
  PRIMARY KEY (id),
  INDEX (origin_name)
) ENGINE = InnoDB DEFAULT CHARSET =utf8 COMMENT '文件信息表';



#initial user data
INSERT INTO user(id,user_name,nick_name,phone,email,password,salt,locked)
values(1,'admin','dh','15268528314','406507191@qq.com','UgWz3MsTsiEFYyGwtaCjWWQDdfg=','cdee2778fa3a4b7387a382d2fc276eb2',1),
  (2,'summer','dh','15268528315','406507192@qq.com','pBT7CZ+B9wcerzsCXw3ZU491kao=','9b35433bd55f4abea372a202bc9c058d',1),
  (3,'solstice','dh','15268528316','406507193@qq.com','Z4gpr3dTn9TzjC3d4YU3iatpRko=','d8c9b1b1a33a49f090611eed064dee5c',1),
  (4,'logger','logger','15268528317','406507194@qq.com','spDJpOeY36kWluls0lgbEeDfNNg=','f00d24e6ae184d6499b096e5472af075',1),
  (5,'guest','匿名','15268528318','406507195@qq.com','7IZU2B2zksnwwUO/riJ+KPXBX3k=','d46925c33a814379b521d6170577e1e5',1);

#initial role data
INSERT INTO role(id,name,description,available)
VALUES (1,'admin','管理员',1),
  (2,'developer','开发人员',1),
  (3,'test','测试人员',1),
  (4,'logger','日志运维人员',1),
  (5,'user','一般客户',1);

#initial permission data
INSERT INTO permission(id,name,type,url,permission,parent_id,parent_ids,available,sort)
VALUES
  (1,'权限管理',1,'','',0,'',1,1),
  (2,'用户管理',2,'／authority/user/list.html','',1,'1',1,1),
  (3,'添加',3,'','sys:admin:add',2,'1,2',1,1),
  (4,'编辑',3,'','sys:admin:edit',2,'1,2',1,2),
  (5,'删除',3,'','sys;admin:del',2,'1,2',1,3),
  (6,'角色管理',2,'/authority/role/list.html','',1,'1',1,2),
  (7,'添加',3,'','sys:admin:add',6,'1,6',1,1),
  (8,'编辑',3,'','sys:admin:edit',6,'1,6',1,2),
  (9,'删除',3,'','sys:admin:del',6,'1,6',1,3),
  (10,'权限设置',2,'/authority/authority/list.html','admin:*',1,'1',1,3),
  (11,'添加',3,'','sys:admin:add',10,'1,10',1,1),
  (12,'编辑',3,'','sys:admin:edit',10,'1,10',1,2),
  (13,'删除',3,'','sys:admin:del',10,'1,10',1,3),
  (14,'代码管理',1,'','',0,'',1,2),
  (15,'代码生成',2,'/code/lists.html',0,14,'14',1,1),
  (16,'生成代码',3,'','sys:admin:edit,sys:developer:edit',15,'14,15',1,1),
  (17,'系统测试',1,'','',0,'',1,3),
  (18,'测试报告',2,'/test/report/lists.html',0,17,'17',1,1),
  (19,'添加',3,'','sys:admin:add,sys:test:add',18,'17,18',1,1),
  (20,'编辑',3,'','sys:admin:edit,sys:test:edit',18,'17,18',1,2),
  (21,'删除',3,'','sys:admin:del,sys;test:del',18,'17,18',1,3),
  (22,'日志管理',1,'','',0,'',1,4),
  (23,'日志查看',2,'/logger/lists.html','admin:*,test:*',22,'22',1,1),
  (24,'编辑',3,'','sys:admin:edit,sys:logger:edi',23,'22,23',1,1),
  (25,'删除',3,'','sys:admin:del,sys:logger:del',23,'22,23',1,2),
  (26,'设置',1,'','',0,'',1,5),
  (27,'用户信息',2,'/user/lists.html','',26,'26',1,1),
  (28,'密码修改',2,'/user/changePassword.html','',26,'26',1,2);

#intial user_role table
INSERT INTO user_role(user_id, role_id, description)
VALUES (1,1,'admin是超级管理员'),
  (2,2,'summer是开发人员'),
  (3,3,'solstice是测试人员'),
  (4,4,'logger是日志运维人员'),
  (5,5,'guest是一般用户');

#initial role_persmission table
INSERT INTO role_permission(role_id,permission_id,description)
VALUES
  (1,1,'admin权限管理'),
  (1,2,'admin用户管理'),
  (1,6,'admin角色管理'),
  (1,10,'admin权限管理'),
  (1,14,'admin代码管理'),
  (1,15,'admin代码管理'),
  (1,17,'admin系统测试'),
  (1,18,'admin系统测试报告'),
  (1,22,'admin日志管理'),
  (1,23,'admin日志查看'),
  (1,26,'admin设置管理'),
  (1,27,'admin用户信息'),
  (1,28,'admin用户密码修改'),
  (2,14,'summer开发人员代码管理'),
  (2,15,'summer开发人员代码生成'),
  (2,26,'summer开发人员设置管理'),
  (2,27,'summer开发人员用户信息'),
  (2,28,'summer开发人员密码修改'),
  (3,17,'test系统测试'),
  (3,18,'test系统测试报告'),
  (3,26,'test测试人员设置管理'),
  (3,27,'test测试人员信息管理'),
  (3,28,'test测试人员密码修改'),
  (4,22,'logger日志管理'),
  (4,23,'logger日志管理查看'),
  (4,26,'logger日志管理人员设置管理'),
  (4,27,'logger日志管理人员信息设置'),
  (4,28,'logger日志管理人员密码修改');

#insert file table datas
INSERT INTO
  file(id,role_id,origin_name,new_name,extension,type,size,save_path)
VALUES
  (null,1,'Java入门到精通.pdf','20180111223155001','pdf','',20000,'/Users/summer/Downloads'),
  (null,1,'Hadoop入门到精通.pdf','20180111223155002','pdf','',50000,'/Users/summer/Downloads'),
  (null,1,'Spark入门到精通.pdf','20180111223155003','pdf','',30000,'/Users/summer/Downloads'),
  (null,1,'Flume入门到精通.pdf','20180111223155004','pdf','',30000,'/Users/summer/Downloads'),
  (null,1,'Elasticsearch入门到精通.pdf','20180111223155005','pdf','',10000,'/Users/summer/Downloads');
