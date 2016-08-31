----------


DsCreditApi 接口调用说明
===================


----------
### 
[TOC]

----------
固定参数说明
----------

1.**公共参数**是指在普通类型的接口中都需要用到的参数，具体公共参数如下：

> **请求公共参数**：
> apiKey：识别用户的作用，唯一值。
> channelNo：为查询接口的对应渠道编号。
> interfaceName：为所需查询的接口名称。
> timestamp：请求的时间戳，签名时使用。
> sign：签名字符串。
> 
> 

2.apiKey和secretKey是平台分发给每个商户使用，可在平台安全功能中的key/secret功能中获取。
**secretKey**：对参数进行签名生成sign参数，请妥善保管，不可泄露！

3.返回的公共参数指接口调用都会返回的几个固定字段，具体参数有：
> **返回公共参数**：
> message：执行结果提示信息。
> code：执行结果状态码，200为请求成功，并获取相关数据，400为某些原因导致请求失败，如参数格式错误、无相关数据等，500为服务器内部错误。401为无权限请求。
> res：查询所需的结果数据，为JSON对象类型。
 

普通类型接口定义
-------------

大部分的接口都为普通类型接口(包括同步接口和异步接口)，调用方式为**POST**请求;
HTTP请求头文本格式类型为：Content-Type: application/json;charset=UTF-8;
传入的参数为**JSON**格式的字符串，请在**raw/body**中提交，具体请参考apidemo代码示例。

#### <i class="icon-file"></i> 接口地址

> http://HOST/credit/api/v1/query

#### <i class="icon-file"></i> 请求参数结构

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
> | :------ | -----: | :-----: | :-------------: |
> | cardno|String | 是    |       所需查询的银行卡卡号  |
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


----------


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
返回参数格式：
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


----------


#### <i class="icon-pencil"></i> 有盾网贷黑名单查询
描述：验证姓名身份证号手机号是否命中网贷黑名单

> 公共参数：
> channelNo：CH1709907004
> interfaceName：mo9acctaccurate
> 
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :------------- |
| idNo|String | 是    |        身份证号码  |
| name|String | 是    |       姓名  |  
| mobile|String | 是    |       手机号码 |
>
```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "channelNo":"CH1709907004",
    "interfaceName":"mo9acctaccurate",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
        "idNo":"33032xxxxxxx20114X",
        "name":"张小小",
        "mobile":"1500000000"
    }
}
```
```
返回参数格式：
{
    "message":"请求成功",
    "res":{
        "status": "不存在黑名单中"
    },
    "code":200
}
```


----------


#### <i class="icon-pencil"></i> 有盾手机号归属地查询
描述：查询手机号的归属地区
> 公共参数：
> channelNo：CH1709907004
> interfaceName：mobileAttribution
> 
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :------------- |
| mobile|String | 是    |        手机号码  |
>
```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "channelNo":"CH1709907004",
    "interfaceName":"mobileAttribution",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
        "mobile":"1500000000"
    }
}
```
```
返回参数格式：
{
    "message":"请求成功",
    "res":{
         "desc": "贵州联通如意通卡",
         "provincename": "贵州",
         "areaname": "贵阳、遵义、安顺",
         "isp": "中国联通",
         "province": "520000",
         "areacode": "0851",
         "city": "520100"
    },
    "code":200
}
```


----------


#### <i class="icon-pencil"></i> 有盾邮箱状态查询
描述：查询输入邮箱的状态
> 公共参数：
> channelNo：CH1709907004
> interfaceName：mailboxAttribution
> 
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :------------- |
| email|String | 是    |        邮箱地址  |
>
```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "channelNo":"CH1709907004",
    "interfaceName":"mailboxAttribution",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
        "email":"1215xxxxxx@qq.com"
    }
}
```
```
返回参数格式：
{
    "message":"请求成功",
    "res":{
         "stauts": "1",
         "statusInfo": "邮箱可用"
    },
    "code":200
}
```


----------


#### <i class="icon-pencil"></i> 有盾身份证泄漏查询
描述：查询身份证是否被泄漏过
> 公共参数：
> channelNo：CH1709907004
> interfaceName：idCardLeak
> 
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :------------- |
| cardno|String | 是    |        身份证号码  |
>
```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "channelNo":"CH1709907004",
    "interfaceName":"idCardLeak",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
        "cardno":"3604xxxxxxxxx50015"
    }
}
```
```
返回参数格式：
{
    "message":"请求成功",
    "res":{
          "res": "1",
          "cardno": "3604xxxxxxxxx50015",
          "tips": "安全"
    },
    "code":200
}
```


----------


#### <i class="icon-pencil"></i> 有盾车辆信息查询(通过车架号)
描述：输入车架号查询该车辆的信息
> 公共参数：
> channelNo：CH1709907004
> interfaceName：carInforByvin
> 
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :------------- |
| vin|String | 是    |        车架号  |
>
```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "channelNo":"CH1709907004",
    "interfaceName":"carInforByvin",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
        "vin":"knaln41xxxxx43773"
    }
}
```
```
返回参数格式：
{
    "message":"请求成功",
    "res":{
          "carinfo": "起亚汽车 起亚Cadenza [凯尊] 三厢 2011款 2.4 手自一体 旗舰版 ( Cadenza [凯尊] 2011年生产 四门5座 )"
    },
    "code":200
}
```


----------


#### <i class="icon-pencil"></i> 有盾盗卡黑名单
描述：验证姓名和身份证是否命中支付反欺诈名单
> 公共参数：
> channelNo：CH1709907004
> interfaceName：antiFraud
> 
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :------------- |
| reqParam|String | 是    |        手机号码； 身份证号码； 银行卡号；填其中一种|
>
```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "channelNo":"CH1709907004",
    "interfaceName":"antiFraud",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
        "reqParam":"62155xxxxxx01036309"
    }
}
```
```
返回参数格式：
{
    "message":"请求成功",
    "res":{
          "status": "未匹配到盗卡信息"
    },
    "code":200
}
```


----------


#### <i class="icon-pencil"></i> 有盾实名认证（人像）
描述：验证姓名、身份证号码是否匹配，并返回按身份证号查询出来的照片。
（特殊情况：如用户的身份证很久未更换，并且查询时用户正处在户口迁移过程中，返
回的照片会与身份证上的照片不一致。 ）
> 公共参数：
> channelNo：CH1709907004
> interfaceName：getNauthWithphoto
> 
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :------------- |
| name_card|String | 是    |       姓名|
| id_card|String | 是    |       身份证号码|
>
```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "channelNo":"CH1709907004",
    "interfaceName":"getNauthWithphoto",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
        "name_card":"杨XX",
        "id_card":"360781xxxxxx082030"
    }
}
```
```
返回参数格式：
{
    "message":"请求成功",
    "res":{
           "status": "认证一致",
           "photo": "IMG1817034037.jpg",
           "base64Photo":"/9PGSEGESGDS=="  //图片的base64字符串
    },
    "code":200
}
```


----------


#### <i class="icon-pencil"></i> 有盾信用魔镜借款信息查询
描述：以个人身份证号为维度，查询该身份证号指定时间区间内的所有不同借款平台数和借款次数。如果没有指定时间区间，则查询该身份证号所有记录的不同借款平台数和借款次数
> 公共参数：
> channelNo：CH1709907004
> interfaceName：borrowingTimes
> 
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :------------- |
| name|String | 是    |       姓名|
| idno|String | 是    |       身份证号码|
| mobile|String | 是    |       手机号码|
| startTime|String | 否    |       查询起始时间（格式：yyyyMMdd）|
| endTime|String | 否   |       身查询结束时间（格式：yyyyMMdd）|
>
```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "channelNo":"CH1709907004",
    "interfaceName":"borrowingTimes",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
         "startTime": "20160101",
         "name": "李XX",
         "endTime": "20170101",
         "idno": "330XXXXXXXXXXX915",
         "mobile": "18000000000"
    }
}
```
```
返回参数格式：
{
    "message":"请求成功",
    "res":{
           "borrowtimes": "0",  //借款次数
           "platnum": "0"       //借款平台数
    },
    "code":200
}
```


----------


#### <i class="icon-pencil"></i> 华道学历查询
描述：查询学历程度
> 公共参数：
> channelNo：CH2022086619
> interfaceName：GetEduFactQueryAttribute
> 
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :------------- |
| idcode|String | 是    |       身份证号码|
| name|String | 是    |       姓名|
>
```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "channelNo":"CH2022086619",
    "interfaceName":"GetEduFactQueryAttribute",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
        "idcode": "3607811xxxxxx182030",
        "name": "杨XX"
    }
}
```
> 返回参数：
>  | 字段名     | 类型    | 描述 |
| :------ | -----:  | :------------- |
| id|String     |       身份证号码|
| graduateTime|String     |       毕业年份|
| enrolDate|String     |       入学年份|
| status|String     |       查询状态结果|
| studyResult|String    |    毕业结论  |
| graduate|String |       毕业院校|
| studyStyle|String |        学历类型|
| userName|String |      姓名|
| educationDegree|String |       学历|
| specialityName|String |       专业|
>
```
返回参数格式：
{
    "message":"请求成功",
    "res":{
           "id": "360781199310182030",
           "graduateTime": "2015",
           "enrolDate": "20110901",
           "status": "查询成功",
           "studyResult": "毕业",
           "graduate": "XX大学",
           "studyStyle": "普通",
           "userName": "杨XX",
           "educationDegree": "本科",
           "specialityName": "考古学"
    },
    "code":200
}
```


----------


#### <i class="icon-pencil"></i> 华道退货次数查询
描述：华道退货次数查询
> 公共参数：
> channelNo：CH2022086619
> interfaceName：GetReturnGoodsFrequency
> 
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :------------- |
| Phone|String | 是    |       手机号码|
| Cycle|String | 是    |       时间段(1,3,6,9,12,24)单位:月|
>
```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "channelNo":"CH2022086619",
    "interfaceName":"GetReturnGoodsFrequency",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
        "Phone": "15800000000",
        "Cycle": "1"
    }
}
```
```
返回参数格式：
{
    "message":"请求成功",
    "res":{
           "PHONE": "15800000000",
           "FREQUENCY": "0", //频次
           "CODE": "200"
    },
    "code":200
}
```


----------


#### <i class="icon-pencil"></i> 华道（房|车|子女|宠物）信息查询
描述：查询个人名下的房，车，子女，及宠物是否存在
> 公共参数：
> channelNo：CH2022086619
> interfaceName：GetIsTag
> 
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :--------|
| Phone|String | 是    |       手机号码|
| Type|String | 是    |       0全部 1是否有房 2是否有车 3 是否有子女 4 是否有宠物|
| Cycle|String | 是    |       时间段(1,3,6,9,12,24)单位:月|
>
```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "channelNo":"CH2022086619",
    "interfaceName":"GetIsTag",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
        "Phone": "15800000000",
        "Type": "0",
        "Cycle": "24"
    }
}
```
```
返回参数格式：
{
    "message":"请求成功",
    "res":{
            "PHONE": "15800000000",
		    "resultMessage": "不是房主,不是车主,没有子女,没有宠物"
    },
    "code":200
}
```


----------


#### <i class="icon-pencil"></i> 通付盾银行卡四要素验证
描述：查询银行卡号码，姓名，身份证号，办卡时绑定的手机号码来验证
> 公共参数：
> channelNo：CH0292901972
> interfaceName：bankCardQuery
> 
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :--------|
| name|String | 是    |       姓名|
| mobile|String | 是    |      手机号码|
| idCard|String | 是    |       身份证号|
| bankCardNum|String | 是    |       银行卡号码|
>
```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "channelNo":"CH0292901972",
    "interfaceName":"bankCardQuery",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
        "bankCardNum":"621XXXXXXXXXXX4440",
        "idCard":"33XXXXXXXXXXXXXX16",
        "name":"王XX",
        "mobile":"1580000000"
    }
}
```
```
返回参数格式：
{
    "message":"请求成功",
    "res":{
            "idCard": "33XXXXXXXXXXXXXX16",
            "bankCardNum": "621XXXXXXXXXXX4440",
            "name": "王XX",
            "statCode": "1200",
            "msg": "银行卡核查一致",
            "mobile": "1580000000"
    },
    "code":200
}
```


----------


#### <i class="icon-pencil"></i> 通付盾身份证多项校验服务接口
描述：通过输入的信息来验证身份可信度
> 公共参数：
> channelNo：CH1276321320
> interfaceName：advanceId
> 
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :--------|
| name|String | 是    |       姓名|
| xb|String | 是    |      性别|
| idCard|String | 是    |       身份证号|
| csrq|String | 是    |       出身年月日|
| whcd|String | 是    |       文化程度|
| zz|String | 是    |       身份所在地|
>
```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "channelNo":"CH1276321320",
    "interfaceName":"advanceId",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
        "idCard":"33XXXXXXXXXXXXXX16",
	    "name":"张XX",
	    "xb":"男性",
	    "csrq":"1978-02-15",
	    "whcd":"本科",
	    "zz":"杭州"
    }
}
```
```
返回参数格式：
{
    "message":"请求成功",
    "res":{
             "whcd": "本科",
	        "idCard": "33XXXXXXXXXXXXXX16",
	        "zz": "临海",
	        "statCode": "1100",
	        "msg": "身份证结果一致",
	        "resultCsrq": "一致",   //根据输入参数来查看字段含义
	        "resultGmsfhm": "一致",
	        "resultZz": "不一致",
	        "resultWhcd": "不一致",
	        "resultZzFs": "相当专科毕业",
	        "name": "张XX",
	        "xb": "男性",
	        "csrq": "1978-02-15",
	        "resultXm": "一致"
    },
    "code":200
}
```


----------


#### <i class="icon-pencil"></i> 通付盾手机在网时长查询
描述：查询手机号在网时长（暂不支持移动号码查询）
> 公共参数：
> channelNo：CH0292901972
> interfaceName：mobinnetQuery
> 
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :--------|
| mobile|String | 是    |       手机号|
>
```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "channelNo":"CH0292901972",
    "interfaceName":"mobinnetQuery",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
        "mobile":"158000000"
    }
}
```
```
返回参数格式：
{
    "message":"请求成功",
    "res":{
	    "state": "36个月以上在网时长",
	    "statCode": "1505",
	    "mobile": "158000000"
    },
    "code":200
}
```


----------


#### <i class="icon-pencil"></i> 通付盾申请比对驾驶证基本信息查询
描述：对比用户驾驶证中的各种信息（按对比信息个数收费，最少对比2条信息，最多对比8条信息，费用区间：3.2-12.8）
**Note:** 该接口是异步接口，请求之后根据获取的taskNo去**任务结果查询接口** [here][1]获取本次的查询结果
> 公共参数：
> channelNo：CH1276321320
> interfaceName：jszverify
> 
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :--------|
| jszh|String | 是    |       驾驶证号|
| xm|String | 是    |       姓名|
| csrq|String | 否    |       出生日期、年龄范围。格式为 yyyyMMdd|
| dabh|String | 否    |     档案编号  |
| zjcx|String | 否    |       准驾车型、准驾车型范围|
| cclzrq|String | 否    |       初次领证日期、驾龄范围。格式为 yyyyMMdd|
| yxqs|String | 否    |      有效期始|
| yxqz|String | 否    |       有效期止|
>
```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "channelNo":"CH1276321320",
    "interfaceName":"jszverify",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
        "jszh": "45XXXXXXXXXXXXXXX78",
	    "xm": "李XX"
    }
}
```
```
返回参数格式：
{
    "message":"请求成功",
	"taskNo":"TAxxxxxxxx",
    "ansy":"true"
    "orderNo":"201608041260963184"
    "code":200
}
```
```
通过“任务结果查询”特殊接口查询后返回的参数格式：
{
    "message":"请求成功",
	"res":{  //输入参数有多少，返回的匹配结果也有多少
		 "result": "匹配正确",  //匹配总结
         "jszh": "匹配正确",
         "xm": "匹配正确",
         "statCode": "0",
         "msg": "请求成功"
	}
    "code":200
}
```


----------


#### <i class="icon-pencil"></i> 通付盾申请驾驶证状态查询
描述：查询驾驶证当前的状态
**Note:** 该接口是异步接口，请求之后根据获取的taskNo去**任务结果查询接口**[here][1]获取本次的查询结果
> 公共参数：
> channelNo：CH1276321320
> interfaceName：jszstatus
> 
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :--------|
| jszh|String | 是    |       驾驶证号|
>
```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "channelNo":"CH1276321320",
    "interfaceName":"jszstatus",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
        "jszh": "45XXXXXXXXXXXXXXX78"
    }
}
```
```
返回参数格式：
{
    "message":"请求成功",
	"taskNo":"TAxxxxxxxx",
    "ansy":"true"
    "orderNo":"201608041260963184"
    "code":200
}
```
```
通过“任务结果查询”特殊接口查询后返回的参数格式：
{
    "message":"请求成功",
	"res":{
		  "result": "匹配正确",
          "zt**": "注销",
          "jszh": "匹配正确",
          "statCode": "0",
          "msg": "请求成功"
	}
    "code":200
}
```


----------


特殊接口类型定义
-------------
这部分接口在请求地址、请求方式和传参上有所不同，需要独立出来说明。
签名规则不变，和普通接口的签名规则相同。


特殊接口详情
-------------
#### <i class="icon-pencil"></i> 任务结果查询
描述：为异步接口提供调用结果查询。

HTTP请求头文本格式类型为：Content-Type: application/json;charset=UTF-8; 
**POST**提交。

<i class="icon-file"></i> 接口地址

> http://HOST/credit/api/v1/task

<i class="icon-file"></i> 请求参数结构

 - 公共参数将不再需要提交channelNo和interfaceName这两个参数，其他保持不变。
 - 每个接口的查询参数（即查询这个接口所需要传入的对应参数，非公共参数）放入一个名为：**payload**的Map中。注意，payload Map中只能填写taskNo参数作为唯一的查询参数。
 - 将公共参数和查询参数拼装成最后的JSON格式。

<i class="icon-file"></i> 签名规则
> **Note:**
>  
> - 基本规则保持不变。
> - 将公共参数（sign、channelNo、interfaceName除外）和接口对应的查询参数拼装成JSON字符串，再使用secretKey进行签名。
> - 注意签名时需要将参数**按照英文字母进行排序**！
```
伪代码，仅供参考，具体参考apidemo中的示例代码。
paramMap = {
	"apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
	"timestamp":1472367902248,
	"taskNo":"TA1157774850"
}
sortMap = sort(paramMap);
sortJsonStr = Json.toString(sortMap);
sign = signUtil.encodeHmacSHA256(sortJsonStr,secretKey);
```

<i class="icon-file"></i> 参数说明
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :--------|
| taskNo|String | 是    |       异步查询返回的taskNo值|
>

```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
        "taskNo": "TA1157774850"
    }
}
```
```
返回参数格式：
{
    "message":"请求成功",
	"res":{
		//返回每个异步接口的查询结果
    }
    "code":200
}
```


----------


#### <i class="icon-pencil"></i> linkface人脸识别实名认证
描述：将自拍脸部头像照片和公安身份证中的头像照片进行对比，返回匹配度和结论。

HTTP请求头文本格式类型为：Content-Type: **multipart/form-data**;
**POST** 表单提交。

<i class="icon-file"></i> 接口地址

> http://HOST/credit/api/v1/linkface

<i class="icon-file"></i> 请求参数结构

 - 将公共参数和查询参数按照multipart/form-data表单提交即可。
 
<i class="icon-file"></i> 签名规则
> **Note:**
>  
> - 基本规则保持不变。
> - 将公共参数（sign除外）和接口对应的查询参数（File类型的参数除外，如下面的“livingImg”）拼装成JSON字符串，再使用secretKey进行签名。
> - 注意签名时需要将参数**按照英文字母进行排序**！
```
伪代码，仅供参考，具体参考apidemo中的示例代码。
paramMap = {
	"apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
	"channelNo":"CH1824755184",
    "interfaceName":"hsVerification",
	"timestamp":1472367902248,
	"name":"王xx",
	"idCard":"3310xxxxxxxxxxxxxxx3421"
}
sortMap = sort(paramMap);
sortJsonStr = Json.toString(sortMap);
sign = signUtil.encodeHmacSHA256(sortJsonStr,secretKey);
```

<i class="icon-file"></i> 参数说明
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :--------|
| name|String | 是    |       姓名|
| idCard|String | 是    |       身份证号码|
| livingImg|File| 是    |       自拍的人脸头像图片|
>

```
提交参数格式：

 apiKey = 161c5ce6-7385-48db-bf68-bc3a8e83,
 sign = 72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92,
 channelNo = CH1824755184,
 interfaceName = hsVerification,
 timestamp = 1472367902248,
 name = 王xx,  //注意，这里中文需要用URLEncoder.encode(name,"utf-8");进行url编码
 idCard = 3310xxxxxxxxxxxxxxx3421

```
```
返回参数格式：
"message":"请求成功！",
    "res":{
        "selfie":{
            "image_id":"117882cd153b4dea94e7ebf0e196ed2e"
        },
        "historical_selfie":{
            "image_id":"6795436fa87a4d538a6bdf298025a400"
        },
        "request_id":"TIDe7696b80a0224107986faac44dda22dc",
        "status":"OK",
        "conclusion":"相似度一般",
        "confidence":0.6207534670829773 //匹配值
    },
"code":200
```


 [1]: #任务结果查询接口