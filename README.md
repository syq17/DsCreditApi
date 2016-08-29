----------


DsCreditApi 接口调用说明
===================


----------


### 
[TOC]








----------
公共参数
----------
公共参数表示无论何种类型的接口每次请求都会传递的参数。
apiKey和secretKey是平台分发给每个商户使用，可在平台安全功能中的key/secret功能中获取。
secretKey：对参数进行签名生成sign参数，请妥善保管，不可泄露！
> **请求公共参数**：
- apiKey：识别用户的作用，唯一值。
- channelNo：为查询接口的对应渠道编号。
- interfaceName：为所需查询的接口名称。
- timestamp：请求的时间戳，签名时使用。
- sign：签名字符串。
>
> **返回公共参数**：
> message：执行结果提示信息。
> code：执行结果状态码，200为请求成功，并获取相关数据，400为某些原因导致请求失败，如参数格式错误、无相关数据等，500为服务器内部错误。401为无权限请求。
> res：查询所需的结果数据，为JSON对象类型。
 

普通类型接口定义
-------------

大部分的接口都为普通类型接口(包括同步接口和异步接口)，调用方式为**POST**请求;
HTTP请求头文本格式类型为：Content-Type: application/json;charset=UTF-8;
传入的参数为**JSON**格式的字符串，请在**raw/body**中提交，具体请参考apidemo代码示例。

#### <i class="icon-file"></i> 接口地址名称

> http://HOST/credit/api/v1/query

#### <i class="icon-file"></i> 请求参数格式

 - 公共参数和每个接口所对应的**查询参数**按照一定的格式提交。
 - 将每个接口的查询参数（即查询这个接口所需要传入的对应参数，非公共参数）放入一个名为：**payload**的Map中和公共参数一起组装成提交的JSON格式。
```
{
	"apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
	"channelNo":"CH1709907004",
	"interfaceName":"mailboxAttribution",
	"sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
	"timestamp":1472367902248,
	"payload":{
		"email":"148XXXXXX50@qq.com"
	}
}
```
#### <i class="icon-file"></i> 签名规则
> **Note:**
> 
> - 对需要传入的参数进行签名来有效防止恶意篡改的伪造请求！
> - 将公共参数（sign除外）和每个接口对应的查询参数拼装成JSON字符串，再使用secretKey进行签名。
> - 注意签名时需要将参数**按照英文字母进行排序**！
```
伪代码，仅供参考，具体参考apidemo中的示例代码。
paramMap = {
	"apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
	"channelNo":"CH1709907004",
	"interfaceName":"mailboxAttribution",
	"timestamp":1472367902248,
	"email":"148XXXXXX50@qq.com"
}
sortMap = sort(paramMap);
sortJsonStr = Json.toString(sortMap);
sign = signUtil.encodeHmacSHA256(sortJsonStr,secretKey);
```

普通接口详情
-------------
#### <i class="icon-pencil"></i> 有盾银行卡bin归属查询
描述：根据银行卡号查询银行卡bin的归属于那个银行和卡类型。

> 公共参数：
> channelNo：CH1709907004
> interfaceName：bankcardbin_query
> 
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :-------------: |
| cardno|String | 是    |       所需查询的银行卡卡号  |
>
```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "channelNo":"CH1709907004",
    "interfaceName":"bankcardbin_query",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
        "cardno":"62220xxxxxxxxxx45702"
    }
}
```
```
返回参数格式：
{
    "message":"请求成功",
    "res":{
        "cardtype":"借记卡",
        "orgcode":"01020000",
        "cardname":"E时代卡",
        "orgname":"工商银行"
    },
    "code":200
}
```

#### <i class="icon-pencil"></i> 有盾IP地址归属地查询
描述：根据IP地址查询该IP地址所属地区

> 公共参数：
> channelNo：CH1709907004
> interfaceName：ip_lattribution
> 
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :-------------: |
| ip|String | 是    |       所需查询的IP地址  |
>
```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "channelNo":"CH1709907004",
    "interfaceName":"ip_lattribution",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
        "ip":"60.22.45.11"
    }
}
```
```
返回参数：
{
    "message":"请求成功",
    "res":{
        "provincename": "辽宁",
	    "desc": "联通",
        "address": "辽宁省营口市",
        "province": "210000",
        "cityname": "营口",
        "city": "210800"
    },
    "code":200
}
```



#### <i class="icon-folder-open"></i> Switch to another document

All your local documents are listed in the document panel. You can switch from one to another by clicking a document in the list or you can toggle documents using <kbd>Ctrl+[</kbd> and <kbd>Ctrl+]</kbd>.

#### <i class="icon-pencil"></i> Rename a document

You can rename the current document by clicking the document title in the navigation bar.

#### <i class="icon-trash"></i> Delete a document

You can delete the current document by clicking <i class="icon-trash"></i> **Delete document** in the document panel.

#### <i class="icon-hdd"></i> Export a document

You can save the current document to a file by clicking <i class="icon-hdd"></i> **Export to disk** from the <i class="icon-provider-stackedit"></i> menu panel.

> **Tip:** Check out the [<i class="icon-upload"></i> Publish a document](#publish-a-document) section for a description of the different output formats.


----------


Synchronization
-------------------

StackEdit can be combined with <i class="icon-provider-gdrive"></i> **Google Drive** and <i class="icon-provider-dropbox"></i> **Dropbox** to have your documents saved in the *Cloud*. The synchronization mechanism takes care of uploading your modifications or downloading the latest version of your documents.

> **Note:**

> - Full access to **Google Drive** or **Dropbox** is required to be able to import any document in StackEdit. Permission restrictions can be configured in the settings.
> - Imported documents are downloaded in your browser and are not transmitted to a server.
> - If you experience problems saving your documents on Google Drive, check and optionally disable browser extensions, such as Disconnect.

#### <i class="icon-refresh"></i> Open a document

You can open a document from <i class="icon-provider-gdrive"></i> **Google Drive** or the <i class="icon-provider-dropbox"></i> **Dropbox** by opening the <i class="icon-refresh"></i> **Synchronize** sub-menu and by clicking **Open from...**. Once opened, any modification in your document will be automatically synchronized with the file in your **Google Drive** / **Dropbox** account.

#### <i class="icon-refresh"></i> Save a document

You can save any document by opening the <i class="icon-refresh"></i> **Synchronize** sub-menu and by clicking **Save on...**. Even if your document is already synchronized with **Google Drive** or **Dropbox**, you can export it to a another location. StackEdit can synchronize one document with multiple locations and accounts.

#### <i class="icon-refresh"></i> Synchronize a document

Once your document is linked to a <i class="icon-provider-gdrive"></i> **Google Drive** or a <i class="icon-provider-dropbox"></i> **Dropbox** file, StackEdit will periodically (every 3 minutes) synchronize it by downloading/uploading any modification. A merge will be performed if necessary and conflicts will be detected.

If you just have modified your document and you want to force the synchronization, click the <i class="icon-refresh"></i> button in the navigation bar.

> **Note:** The <i class="icon-refresh"></i> button is disabled when you have no document to synchronize.

#### <i class="icon-refresh"></i> Manage document synchronization

Since one document can be synchronized with multiple locations, you can list and manage synchronized locations by clicking <i class="icon-refresh"></i> **Manage synchronization** in the <i class="icon-refresh"></i> **Synchronize** sub-menu. This will let you remove synchronization locations that are associated to your document.

> **Note:** If you delete the file from **Google Drive** or from **Dropbox**, the document will no longer be synchronized with that location.

----------


Publication
-------------

Once you are happy with your document, you can publish it on different websites directly from StackEdit. As for now, StackEdit can publish on **Blogger**, **Dropbox**, **Gist**, **GitHub**, **Google Drive**, **Tumblr**, **WordPress** and on any SSH server.

#### <i class="icon-upload"></i> Publish a document

You can publish your document by opening the <i class="icon-upload"></i> **Publish** sub-menu and by choosing a website. In the dialog box, you can choose the publication format:

- Markdown, to publish the Markdown text on a website that can interpret it (**GitHub** for instance),
- HTML, to publish the document converted into HTML (on a blog for example),
- Template, to have a full control of the output.

> **Note:** The default template is a simple webpage wrapping your document in HTML format. You can customize it in the **Advanced** tab of the <i class="icon-cog"></i> **Settings** dialog.

#### <i class="icon-upload"></i> Update a publication

After publishing, StackEdit will keep your document linked to that publication which makes it easy for you to update it. Once you have modified your document and you want to update your publication, click on the <i class="icon-upload"></i> button in the navigation bar.

> **Note:** The <i class="icon-upload"></i> button is disabled when your document has not been published yet.

#### <i class="icon-upload"></i> Manage document publication

Since one document can be published on multiple locations, you can list and manage publish locations by clicking <i class="icon-upload"></i> **Manage publication** in the <i class="icon-provider-stackedit"></i> menu panel. This will let you remove publication locations that are associated to your document.

> **Note:** If the file has been removed from the website or the blog, the document will no longer be published on that location.

----------


Markdown Extra
--------------------

StackEdit supports **Markdown Extra**, which extends **Markdown** syntax with some nice features.

> **Tip:** You can disable any **Markdown Extra** feature in the **Extensions** tab of the <i class="icon-cog"></i> **Settings** dialog.

> **Note:** You can find more information about **Markdown** syntax [here][2] and **Markdown Extra** extension [here][3].


### Tables

**Markdown Extra** has a special syntax for tables:

Item     | Value
-------- | ---
Computer | $1600
Phone    | $12
Pipe     | $1

You can specify column alignment with one or two colons:

| Item     | Value | Qty   |
| :------- | ----: | :---: |
| Computer | $1600 |  5    |
| Phone    | $12   |  12   |
| Pipe     | $1    |  234  |


### Definition Lists

**Markdown Extra** has a special syntax for definition lists too:

Term 1
Term 2
:   Definition A
:   Definition B

Term 3

:   Definition C

:   Definition D

	> part of definition D


### Fenced code blocks

GitHub's fenced code blocks are also supported with **Highlight.js** syntax highlighting:

```
// Foo
var bar = 0;
```

> **Tip:** To use **Prettify** instead of **Highlight.js**, just configure the **Markdown Extra** extension in the <i class="icon-cog"></i> **Settings** dialog.

> **Note:** You can find more information:

> - about **Prettify** syntax highlighting [here][5],
> - about **Highlight.js** syntax highlighting [here][6].


### Footnotes

You can create footnotes like this[^footnote].

  [^footnote]: Here is the *text* of the **footnote**.


### SmartyPants

SmartyPants converts ASCII punctuation characters into "smart" typographic punctuation HTML entities. For example:

|                  | ASCII                        | HTML              |
 ----------------- | ---------------------------- | ------------------
| Single backticks | `'Isn't this fun?'`            | 'Isn't this fun?' |
| Quotes           | `"Isn't this fun?"`            | "Isn't this fun?" |
| Dashes           | `-- is en-dash, --- is em-dash` | -- is en-dash, --- is em-dash |


### Table of contents

You can insert a table of contents using the marker `[TOC]`:

[TOC]


### MathJax

You can render *LaTeX* mathematical expressions using **MathJax**, as on [math.stackexchange.com][1]:

The *Gamma function* satisfying $\Gamma(n) = (n-1)!\quad\forall n\in\mathbb N$ is via the Euler integral

$$
\Gamma(z) = \int_0^\infty t^{z-1}e^{-t}dt\,.
$$

> **Tip:** To make sure mathematical expressions are rendered properly on your website, include **MathJax** into your template:

```
<script type="text/javascript" src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS_HTML"></script>
```

> **Note:** You can find more information about **LaTeX** mathematical expressions [here][4].


### UML diagrams

You can also render sequence diagrams like this:

```sequence
Alice->Bob: Hello Bob, how are you?
Note right of Bob: Bob thinks
Bob-->Alice: I am good thanks!
```

And flow charts like this:

```flow
st=>start: Start
e=>end
op=>operation: My Operation
cond=>condition: Yes or No?

st->op->cond
cond(yes)->e
cond(no)->op
```

> **Note:** You can find more information:

> - about **Sequence diagrams** syntax [here][7],
> - about **Flow charts** syntax [here][8].

### Support StackEdit

[![](https://cdn.monetizejs.com/resources/button-32.png)](https://monetizejs.com/authorize?client_id=ESTHdCYOi18iLhhO&summary=true)

  [^stackedit]: [StackEdit](https://stackedit.io/) is a full-featured, open-source Markdown editor based on PageDown, the Markdown library used by Stack Overflow and the other Stack Exchange sites.


  [1]: http://math.stackexchange.com/
  [2]: http://daringfireball.net/projects/markdown/syntax "Markdown"
  [3]: https://github.com/jmcmanus/pagedown-extra "Pagedown Extra"
  [4]: http://meta.math.stackexchange.com/questions/5020/mathjax-basic-tutorial-and-quick-reference
  [5]: https://code.google.com/p/google-code-prettify/
  [6]: http://highlightjs.org/
  [7]: http://bramp.github.io/js-sequence-diagrams/
  [8]: http://adrai.github.io/flowchart.js/
