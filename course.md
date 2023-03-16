---
title: course v1.0.0
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
code_clipboard: true
highlight_theme: darkula
headingLevel: 2
generator: "@tarslib/widdershins v4.0.17"

---

# course

> v1.0.0

Base URLs:

* <a href="http://dev-cn.your-api-server.com">开发环境: http://dev-cn.your-api-server.com</a>

# TagController

## GET SearchTag

GET /Tag/searchTag

> Body 请求参数

```json
"string"
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|string| 否 |none|

> 返回示例

> 成功

```json
{
  "tagId": 0,
  "tagGroupName": "",
  "tagName": ""
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

*com.example.entity.Tag*

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|

## POST linkTagGroup

POST /Tag/linkTagGroup

> Body 请求参数

```json
{}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|

> 返回示例

> 成功

```json
0
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|integer|

## POST updateTag

POST /Tag/updateTag

> Body 请求参数

```json
{}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|

> 返回示例

> 成功

```json
0
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|integer|

## POST InsertTag

POST /Tag/insertTag

> Body 请求参数

```json
{}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|

> 返回示例

> 成功

```json
0
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|integer|

## GET queryTagList

GET /Tag/queryTagList

> 返回示例

> 成功

```json
[
  {
    "tagId": 0,
    "tagGroupName": "",
    "tagName": ""
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST deleteTag

POST /Tag/deleteTag

> Body 请求参数

```json
"string"
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|string| 否 |none|

> 返回示例

> 成功

```json
0
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|integer|

# CasesController

## GET getAll

GET /cases/all

> 返回示例

> 成功

```json
[
  {
    "caseId": 0,
    "knowledgeId": 0,
    "caseInfo": "",
    "isCode": 0,
    "type": ""
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|*anonymous*|[[Cases](#schemacases)]|false|none||none|
|» caseId|integer|false|none||none|
|» knowledgeId|integer|false|none||none|
|» caseInfo|string|false|none||none|
|» isCode|integer|false|none||none|
|» type|string|false|none||none|

## GET getCasesByKId

GET /cases/byKId/{knowledgeId}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|knowledgeId|path|string| 是 |none|

> 返回示例

> 成功

```json
[
  {
    "caseId": 0,
    "knowledgeId": 0,
    "caseInfo": "",
    "isCode": 0,
    "type": ""
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|*anonymous*|[[Cases](#schemacases)]|false|none||none|
|» caseId|integer|false|none||none|
|» knowledgeId|integer|false|none||none|
|» caseInfo|string|false|none||none|
|» isCode|integer|false|none||none|
|» type|string|false|none||none|

## POST insertCases

POST /cases/insert

> Body 请求参数

```json
{
  "caseId": 0,
  "knowledgeId": 0,
  "caseInfo": "string",
  "isCode": 0,
  "type": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[Cases](#schemacases)| 否 |none|

> 返回示例

> 成功

```json
0
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|integer|

## PUT updateCases

PUT /cases/update

> Body 请求参数

```json
{
  "caseId": 0,
  "knowledgeId": 0,
  "caseInfo": "string",
  "isCode": 0,
  "type": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[Cases](#schemacases)| 否 |none|

> 返回示例

> 成功

```json
0
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|integer|

## DELETE deleteCases

DELETE /cases/delete/{casesId}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|casesId|path|string| 是 |none|

> 返回示例

> 成功

```json
0
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|integer|

# CourseController

## GET queryCourseList

GET /course/queryCourseList

> 返回示例

> 成功

```json
[
  {
    "courseName": "",
    "courseId": "",
    "introduction": "",
    "textbook": "",
    "tags": "",
    "teacher": ""
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|*anonymous*|[[Course](#schemacourse)]|false|none||none|
|» courseName|string|false|none||none|
|» courseId|string|false|none||none|
|» introduction|string|false|none||none|
|» textbook|string|false|none||none|
|» tags|string|false|none||none|
|» teacher|string|false|none||none|

## GET SearchCourse

GET /course/SearchCourse

> Body 请求参数

```json
"string"
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|string| 否 |none|

> 返回示例

> 成功

```json
{
  "courseName": "",
  "courseId": "",
  "introduction": "",
  "textbook": "",
  "tags": "",
  "teacher": ""
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|[Course](#schemacourse)|

## POST InsertCourse

POST /course/insertCourse

> Body 请求参数

```json
{
  "courseName": "string",
  "courseId": "string",
  "introduction": "string",
  "textbook": "string",
  "tags": "string",
  "teacher": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[Course](#schemacourse)| 否 |none|

> 返回示例

> 成功

```json
0
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|integer|

## POST updateCourse

POST /course/updateCourse

> Body 请求参数

```json
{
  "courseName": "string",
  "courseId": "string",
  "introduction": "string",
  "textbook": "string",
  "tags": "string",
  "teacher": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[Course](#schemacourse)| 否 |none|

> 返回示例

> 成功

```json
0
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|integer|

## POST deleteCourse

POST /course/deleteCourse

> Body 请求参数

```json
"string"
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|string| 否 |none|

> 返回示例

> 成功

```json
0
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|integer|

# TagGroupController

## GET queryTagGroupList

GET /queryTagGroupList

> 返回示例

> 成功

```json
[
  {
    "tagGroupId": 0,
    "tagGroupName": ""
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|*anonymous*|[[TagGroup](#schemataggroup)]|false|none||none|
|» tagGroupId|integer|false|none||none|
|» tagGroupName|string|false|none||none|

## GET SearchTagGroup

GET /searchTagGroup

> Body 请求参数

```json
"string"
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|string| 否 |none|

> 返回示例

> 成功

```json
{
  "tagGroupId": 0,
  "tagGroupName": ""
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|[TagGroup](#schemataggroup)|

## POST InsertTagGroup

POST /insertTagGroup

> Body 请求参数

```json
{
  "tagGroupId": 0,
  "tagGroupName": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[TagGroup](#schemataggroup)| 否 |none|

> 返回示例

> 成功

```json
0
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|integer|

## POST UpdateTagGroup

POST /updateTagGroup

> Body 请求参数

```json
{
  "tagGroupId": 0,
  "tagGroupName": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[TagGroup](#schemataggroup)| 否 |none|

> 返回示例

> 成功

```json
0
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|integer|

## POST deleteTagGroup

POST /deleteTagGroup

> Body 请求参数

```json
"string"
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|string| 否 |none|

> 返回示例

> 成功

```json
0
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|integer|

# KnowledgeController

## GET getAll

GET /knowledge/all

> 返回示例

> 成功

```json
[
  {
    "knowledgeId": 0,
    "knowledgeName": "",
    "description": "",
    "emphasis": "",
    "preKnowledges": "",
    "labels": ""
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|*anonymous*|[[Knowledge](#schemaknowledge)]|false|none||none|
|» knowledgeId|integer|false|none||none|
|» knowledgeName|string|false|none||none|
|» description|string|false|none||none|
|» emphasis|string|false|none||none|
|» preKnowledges|string|false|none||none|
|» labels|string|false|none||none|

## GET getKnowledgeByName

GET /knowledge/byName/{name}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|name|path|string| 是 |none|

> 返回示例

> 成功

```json
[
  {
    "knowledgeId": 0,
    "knowledgeName": "",
    "description": "",
    "emphasis": "",
    "preKnowledges": "",
    "labels": ""
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|*anonymous*|[[Knowledge](#schemaknowledge)]|false|none||none|
|» knowledgeId|integer|false|none||none|
|» knowledgeName|string|false|none||none|
|» description|string|false|none||none|
|» emphasis|string|false|none||none|
|» preKnowledges|string|false|none||none|
|» labels|string|false|none||none|

## POST insertKnowledge

POST /knowledge/insert

> Body 请求参数

```json
{
  "knowledgeId": 0,
  "knowledgeName": "string",
  "description": "string",
  "emphasis": "string",
  "preKnowledges": "string",
  "labels": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[Knowledge](#schemaknowledge)| 否 |none|

> 返回示例

> 成功

```json
0
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|integer|

## PUT updateKnowledge

PUT /knowledge/update

> Body 请求参数

```json
{
  "knowledgeId": 0,
  "knowledgeName": "string",
  "description": "string",
  "emphasis": "string",
  "preKnowledges": "string",
  "labels": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[Knowledge](#schemaknowledge)| 否 |none|

> 返回示例

> 成功

```json
0
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|integer|

## DELETE deleteKnowledge

DELETE /knowledge/delete/{name}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|name|path|string| 是 |none|

> 返回示例

> 成功

```json
0
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|integer|

# 数据模型

<h2 id="tocS_Cases">Cases</h2>

<a id="schemacases"></a>
<a id="schema_Cases"></a>
<a id="tocScases"></a>
<a id="tocscases"></a>

```json
{
  "caseId": 0,
  "knowledgeId": 0,
  "caseInfo": "string",
  "isCode": 0,
  "type": "string"
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|caseId|integer|false|none||none|
|knowledgeId|integer|false|none||none|
|caseInfo|string|false|none||none|
|isCode|integer|false|none||none|
|type|string|false|none||none|

<h2 id="tocS_Course">Course</h2>

<a id="schemacourse"></a>
<a id="schema_Course"></a>
<a id="tocScourse"></a>
<a id="tocscourse"></a>

```json
{
  "courseName": "string",
  "courseId": "string",
  "introduction": "string",
  "textbook": "string",
  "tags": "string",
  "teacher": "string"
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|courseName|string|false|none||none|
|courseId|string|false|none||none|
|introduction|string|false|none||none|
|textbook|string|false|none||none|
|tags|string|false|none||none|
|teacher|string|false|none||none|

<h2 id="tocS_TagGroup">TagGroup</h2>

<a id="schemataggroup"></a>
<a id="schema_TagGroup"></a>
<a id="tocStaggroup"></a>
<a id="tocstaggroup"></a>

```json
{
  "tagGroupId": 0,
  "tagGroupName": "string"
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|tagGroupId|integer|false|none||none|
|tagGroupName|string|false|none||none|

<h2 id="tocS_Knowledge">Knowledge</h2>

<a id="schemaknowledge"></a>
<a id="schema_Knowledge"></a>
<a id="tocSknowledge"></a>
<a id="tocsknowledge"></a>

```json
{
  "knowledgeId": 0,
  "knowledgeName": "string",
  "description": "string",
  "emphasis": "string",
  "preKnowledges": "string",
  "labels": "string"
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|knowledgeId|integer|false|none||none|
|knowledgeName|string|false|none||none|
|description|string|false|none||none|
|emphasis|string|false|none||none|
|preKnowledges|string|false|none||none|
|labels|string|false|none||none|

