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
        "name_card":"杨振坤",
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
           "base64Photo":"/9PGSEGESGDS=="
    },
    "code":200
}
```
#### <i class="icon-pencil"></i> 有数企业综合信息
描述：企业信息360度全方位查询
> 公共参数：
> channelNo：CH2111907949
> interfaceName：companyComplex
> 
> 查询参数：
> | 字段名     | 类型 | 是否必填   | 描述 |
| :------ | -----: | :-----: | :------------- |
| name|String | 是    |       企业名称或者企业注册号|
>
```
提交参数格式：
{
    "apiKey":"161c5ce6-7385-48db-bf68-bc3a8e83",
    "channelNo":"CH2111907949",
    "interfaceName":"companyComplex",
    "sign":"72c787c60792445fbe9e9e1276f5ef7e0161c7e1889bfb92",
    "timestamp":1472367902248,
    "payload":{
        "name":"杭州金爵洁具有限公司"
    }
}
```
```
返回参数格式：
{
    "message":"请求成功",
    "res":{
           请参考有数金服api接口返回格式中的data中的结构
    },
    "code":200
}
```





特殊接口详情
-------------
