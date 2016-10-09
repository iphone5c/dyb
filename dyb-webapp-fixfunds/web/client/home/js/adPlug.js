/*
 *非模态对话框
 *
 */
var adCityData 		={"servertime":"2016-03-12 10:09:57","data":[{"id":"","name":"全部","chindren":[]},{"id":110000,"name":"北京市","chindren":[{"id":1,"name":"北京"}]},{"id":120000,"name":"天津市","chindren":[{"id":3,"name":"天津"}]},{"id":130000,"name":"河北省","chindren":[{"id":5,"name":"石家庄市"},{"id":6,"name":"唐山市"},{"id":7,"name":"秦皇岛市"},{"id":8,"name":"邯郸市"},{"id":9,"name":"邢台市"},{"id":10,"name":"保定市"},{"id":11,"name":"张家口市"},{"id":12,"name":"承德市"},{"id":13,"name":"沧州市"},{"id":14,"name":"廊坊市"},{"id":15,"name":"衡水市"},{"id":387,"name":"涿州市"},{"id":5170010,"name":"藁城市"},{"id":5170026,"name":"遵化市"}]},{"id":140000,"name":"山西省","chindren":[{"id":16,"name":"太原市"},{"id":17,"name":"大同市"},{"id":18,"name":"阳泉市"},{"id":19,"name":"长治市"},{"id":20,"name":"晋城市"},{"id":21,"name":"朔州市"},{"id":22,"name":"晋中市"},{"id":23,"name":"运城市"},{"id":24,"name":"忻州市"},{"id":25,"name":"临汾市"},{"id":26,"name":"吕梁市"}]},{"id":150000,"name":"内蒙古自治区","chindren":[{"id":27,"name":"呼和浩特市"},{"id":28,"name":"包头市"},{"id":29,"name":"乌海市"},{"id":30,"name":"赤峰市"},{"id":31,"name":"通辽市"},{"id":32,"name":"鄂尔多斯市"},{"id":33,"name":"呼伦贝尔市"},{"id":34,"name":"巴彦淖尔市"},{"id":35,"name":"乌兰察布市"},{"id":36,"name":"兴安盟"},{"id":37,"name":"锡林郭勒盟"},{"id":38,"name":"阿拉善盟"}]},{"id":210000,"name":"辽宁省","chindren":[{"id":39,"name":"沈阳市"},{"id":40,"name":"大连市"},{"id":41,"name":"鞍山市"},{"id":42,"name":"抚顺市"},{"id":43,"name":"本溪市"},{"id":44,"name":"丹东市"},{"id":45,"name":"锦州市"},{"id":46,"name":"营口市"},{"id":47,"name":"阜新市"},{"id":48,"name":"辽阳市"},{"id":49,"name":"盘锦市"},{"id":50,"name":"铁岭市"},{"id":51,"name":"朝阳市"},{"id":52,"name":"葫芦岛市"}]},{"id":220000,"name":"吉林省","chindren":[{"id":53,"name":"长春市"},{"id":54,"name":"吉林市"},{"id":55,"name":"四平市"},{"id":56,"name":"辽源市"},{"id":57,"name":"通化市"},{"id":58,"name":"白山市"},{"id":59,"name":"松原市"},{"id":60,"name":"白城市"},{"id":61,"name":"延边朝鲜族自治州"},{"id":5170008,"name":"德惠市"}]},{"id":230000,"name":"黑龙江省","chindren":[{"id":62,"name":"哈尔滨市"},{"id":63,"name":"齐齐哈尔市"},{"id":64,"name":"鸡西市"},{"id":65,"name":"鹤岗市"},{"id":66,"name":"双鸭山市"},{"id":67,"name":"大庆市"},{"id":68,"name":"伊春市"},{"id":69,"name":"佳木斯市"},{"id":70,"name":"七台河市"},{"id":71,"name":"牡丹江市"},{"id":72,"name":"黑河市"},{"id":73,"name":"绥化市"},{"id":74,"name":"大兴安岭地区"}]},{"id":310000,"name":"上海市","chindren":[{"id":75,"name":"上海"}]},{"id":320000,"name":"江苏省","chindren":[{"id":77,"name":"南京市"},{"id":78,"name":"无锡市"},{"id":79,"name":"徐州市"},{"id":80,"name":"常州市"},{"id":81,"name":"苏州市"},{"id":82,"name":"南通市"},{"id":83,"name":"连云港市"},{"id":84,"name":"淮安市"},{"id":85,"name":"盐城市"},{"id":86,"name":"扬州市"},{"id":87,"name":"镇江市"},{"id":88,"name":"泰州市"},{"id":89,"name":"宿迁市"}]},{"id":330000,"name":"浙江省","chindren":[{"id":90,"name":"杭州市"},{"id":91,"name":"宁波市"},{"id":92,"name":"温州市"},{"id":93,"name":"嘉兴市"},{"id":94,"name":"湖州市"},{"id":95,"name":"绍兴市"},{"id":96,"name":"金华市"},{"id":97,"name":"衢州市"},{"id":98,"name":"舟山市"},{"id":99,"name":"台州市"},{"id":100,"name":"丽水市"}]},{"id":340000,"name":"安徽省","chindren":[{"id":101,"name":"合肥市"},{"id":102,"name":"芜湖市"},{"id":103,"name":"蚌埠市"},{"id":104,"name":"淮南市"},{"id":105,"name":"马鞍山市"},{"id":106,"name":"淮北市"},{"id":107,"name":"铜陵市"},{"id":108,"name":"安庆市"},{"id":109,"name":"黄山市"},{"id":110,"name":"滁州市"},{"id":111,"name":"阜阳市"},{"id":112,"name":"宿州市"},{"id":113,"name":"巢湖市"},{"id":114,"name":"六安市"},{"id":115,"name":"亳州市"},{"id":116,"name":"池州市"},{"id":117,"name":"宣城市"}]},{"id":350000,"name":"福建省","chindren":[{"id":118,"name":"福州市"},{"id":119,"name":"厦门市"},{"id":120,"name":"莆田市"},{"id":121,"name":"三明市"},{"id":122,"name":"泉州市"},{"id":123,"name":"漳州市"},{"id":124,"name":"南平市"},{"id":125,"name":"龙岩市"},{"id":126,"name":"宁德市"}]},{"id":360000,"name":"江西省","chindren":[{"id":127,"name":"南昌市"},{"id":128,"name":"景德镇市"},{"id":129,"name":"萍乡市"},{"id":130,"name":"九江市"},{"id":131,"name":"新余市"},{"id":132,"name":"鹰潭市"},{"id":133,"name":"赣州市"},{"id":134,"name":"吉安市"},{"id":135,"name":"宜春市"},{"id":136,"name":"抚州市"},{"id":137,"name":"上饶市"}]},{"id":370000,"name":"山东省","chindren":[{"id":138,"name":"济南市"},{"id":139,"name":"青岛市"},{"id":140,"name":"淄博市"},{"id":141,"name":"枣庄市"},{"id":142,"name":"东营市"},{"id":143,"name":"烟台市"},{"id":144,"name":"潍坊市"},{"id":145,"name":"济宁市"},{"id":146,"name":"泰安市"},{"id":147,"name":"威海市"},{"id":148,"name":"日照市"},{"id":149,"name":"莱芜市"},{"id":150,"name":"临沂市"},{"id":151,"name":"德州市"},{"id":152,"name":"聊城市"},{"id":153,"name":"滨州市"},{"id":382,"name":"菏泽市"}]},{"id":410000,"name":"河南省","chindren":[{"id":155,"name":"郑州市"},{"id":156,"name":"开封市"},{"id":157,"name":"洛阳市"},{"id":158,"name":"平顶山市"},{"id":159,"name":"安阳市"},{"id":160,"name":"鹤壁市"},{"id":161,"name":"新乡市"},{"id":162,"name":"焦作市"},{"id":163,"name":"濮阳市"},{"id":164,"name":"许昌市"},{"id":165,"name":"漯河市"},{"id":166,"name":"三门峡市"},{"id":167,"name":"南阳市"},{"id":168,"name":"商丘市"},{"id":169,"name":"信阳市"},{"id":170,"name":"周口市"},{"id":171,"name":"驻马店市"},{"id":360,"name":"济源市"}]},{"id":420000,"name":"湖北省","chindren":[{"id":172,"name":"武汉市"},{"id":173,"name":"黄石市"},{"id":174,"name":"十堰市"},{"id":175,"name":"宜昌市"},{"id":176,"name":"襄樊市"},{"id":177,"name":"鄂州市"},{"id":178,"name":"荆门市"},{"id":179,"name":"孝感市"},{"id":180,"name":"荆州市"},{"id":181,"name":"黄冈市"},{"id":182,"name":"咸宁市"},{"id":183,"name":"随州市"},{"id":184,"name":"恩施州"},{"id":185,"name":"省直辖行政单位"},{"id":361,"name":"仙桃市"},{"id":362,"name":"天门市"},{"id":363,"name":"潜江市"},{"id":364,"name":"神农架林区"}]},{"id":430000,"name":"湖南省","chindren":[{"id":186,"name":"长沙市"},{"id":187,"name":"株洲市"},{"id":188,"name":"湘潭市"},{"id":189,"name":"衡阳市"},{"id":190,"name":"邵阳市"},{"id":191,"name":"岳阳市"},{"id":192,"name":"常德市"},{"id":193,"name":"张家界市"},{"id":194,"name":"益阳市"},{"id":195,"name":"郴州市"},{"id":196,"name":"永州市"},{"id":197,"name":"怀化市"},{"id":198,"name":"娄底市"},{"id":199,"name":"湘西土家族苗族自治州"},{"id":5170015,"name":"耒阳市"},{"id":5170017,"name":"韶山市"},{"id":5170018,"name":"吉首市"}]},{"id":440000,"name":"广东省","chindren":[{"id":200,"name":"广州市"},{"id":201,"name":"韶关市"},{"id":202,"name":"深圳市"},{"id":203,"name":"珠海市"},{"id":204,"name":"汕头市"},{"id":205,"name":"佛山市"},{"id":206,"name":"江门市"},{"id":207,"name":"湛江市"},{"id":208,"name":"茂名市"},{"id":209,"name":"肇庆市"},{"id":210,"name":"惠州市"},{"id":211,"name":"梅州市"},{"id":212,"name":"汕尾市"},{"id":213,"name":"河源市"},{"id":214,"name":"阳江市"},{"id":215,"name":"清远市"},{"id":216,"name":"东莞市"},{"id":217,"name":"中山市"},{"id":218,"name":"潮州市"},{"id":219,"name":"揭阳市"},{"id":220,"name":"云浮市"}]},{"id":450000,"name":"广西壮族自治区","chindren":[{"id":221,"name":"南宁市"},{"id":222,"name":"柳州市"},{"id":223,"name":"桂林市"},{"id":224,"name":"梧州市"},{"id":225,"name":"北海市"},{"id":226,"name":"防城港市"},{"id":227,"name":"钦州市"},{"id":228,"name":"贵港市"},{"id":229,"name":"玉林市"},{"id":230,"name":"百色市"},{"id":231,"name":"贺州市"},{"id":232,"name":"河池市"},{"id":233,"name":"来宾市"},{"id":234,"name":"崇左市"}]},{"id":460000,"name":"海南省","chindren":[{"id":235,"name":"海口市"},{"id":236,"name":"三亚市"},{"id":237,"name":"省直辖"},{"id":349,"name":"琼海市"},{"id":367,"name":"万宁市"},{"id":368,"name":"五指山市"},{"id":369,"name":"东方市"},{"id":370,"name":"儋州市"},{"id":371,"name":"临高县"},{"id":372,"name":"澄迈县"},{"id":373,"name":"定安县"},{"id":374,"name":"屯昌县"},{"id":375,"name":"昌江黎族自治县"},{"id":376,"name":"琼中县"},{"id":377,"name":"陵水黎族自治县"},{"id":378,"name":"保亭黎族县"},{"id":379,"name":"乐东黎族自治县"},{"id":380,"name":"白沙黎族自治县"},{"id":381,"name":"文昌市"}]},{"id":500000,"name":"重庆市","chindren":[{"id":238,"name":"重庆"}]},{"id":510000,"name":"四川省","chindren":[{"id":241,"name":"成都市"},{"id":242,"name":"自贡市"},{"id":243,"name":"攀枝花市"},{"id":244,"name":"泸州市"},{"id":245,"name":"德阳市"},{"id":246,"name":"绵阳市"},{"id":247,"name":"广元市"},{"id":248,"name":"遂宁市"},{"id":249,"name":"内江市"},{"id":250,"name":"乐山市"},{"id":251,"name":"南充市"},{"id":252,"name":"眉山市"},{"id":253,"name":"宜宾市"},{"id":254,"name":"广安市"},{"id":255,"name":"达州市"},{"id":256,"name":"雅安市"},{"id":257,"name":"巴中市"},{"id":258,"name":"资阳市"},{"id":259,"name":"阿坝州"},{"id":260,"name":"甘孜藏族自治州"},{"id":261,"name":"凉山彝族自治州"}]},{"id":520000,"name":"贵州省","chindren":[{"id":262,"name":"贵阳市"},{"id":263,"name":"六盘水市"},{"id":264,"name":"遵义市"},{"id":265,"name":"安顺市"},{"id":266,"name":"铜仁市"},{"id":267,"name":"黔西南布依族苗族自治州"},{"id":268,"name":"毕节市"},{"id":269,"name":"黔东南苗族侗族自治州"},{"id":270,"name":"黔南布依族苗族自治州"}]},{"id":530000,"name":"云南省","chindren":[{"id":271,"name":"昆明市"},{"id":272,"name":"曲靖市"},{"id":273,"name":"玉溪市"},{"id":274,"name":"保山市"},{"id":275,"name":"昭通市"},{"id":276,"name":"丽江市"},{"id":277,"name":"思茅市"},{"id":278,"name":"临沧市"},{"id":279,"name":"楚雄彝族自治州"},{"id":280,"name":"红河州"},{"id":281,"name":"文山州"},{"id":282,"name":"西双版纳傣族自治州"},{"id":283,"name":"大理白族自治州"},{"id":284,"name":"德宏州"},{"id":285,"name":"怒江傈僳族自治州"},{"id":286,"name":"迪庆藏族自治州"},{"id":350,"name":"普洱市"},{"id":351,"name":"文山州"},{"id":352,"name":"红河州"},{"id":353,"name":"西双版纳傣族自治州"},{"id":354,"name":"楚雄州"},{"id":355,"name":"大理州"},{"id":356,"name":"德宏州"},{"id":357,"name":"怒江州"},{"id":358,"name":"迪庆州"}]},{"id":540000,"name":"西藏自治区","chindren":[{"id":287,"name":"拉萨市"},{"id":288,"name":"昌都地区"},{"id":289,"name":"山南地区"},{"id":290,"name":"日喀则地区"},{"id":291,"name":"那曲地区"},{"id":292,"name":"阿里地区"},{"id":293,"name":"林芝地区"}]},{"id":610000,"name":"陕西省","chindren":[{"id":294,"name":"西安市"},{"id":295,"name":"铜川市"},{"id":296,"name":"宝鸡市"},{"id":297,"name":"咸阳市"},{"id":298,"name":"渭南市"},{"id":299,"name":"延安市"},{"id":300,"name":"汉中市"},{"id":301,"name":"榆林市"},{"id":302,"name":"安康市"},{"id":303,"name":"商洛市"}]},{"id":620000,"name":"甘肃省","chindren":[{"id":304,"name":"兰州市"},{"id":305,"name":"嘉峪关市"},{"id":306,"name":"金昌市"},{"id":307,"name":"白银市"},{"id":308,"name":"天水市"},{"id":309,"name":"武威市"},{"id":310,"name":"张掖市"},{"id":311,"name":"平凉市"},{"id":312,"name":"酒泉市"},{"id":313,"name":"庆阳市"},{"id":314,"name":"定西市"},{"id":315,"name":"陇南市"},{"id":365,"name":"临夏回族自治州"},{"id":366,"name":"甘南藏族自治州"},{"id":383,"name":"武威市"}]},{"id":630000,"name":"青海省","chindren":[{"id":318,"name":"西宁市"},{"id":319,"name":"海东地区"},{"id":320,"name":"海北藏族自治州"},{"id":321,"name":"黄南藏族自治州"},{"id":322,"name":"海南藏族自治州"},{"id":323,"name":"果洛藏族自治州"},{"id":324,"name":"玉树藏族自治州"},{"id":325,"name":"海西州"}]},{"id":640000,"name":"宁夏回族自治区","chindren":[{"id":326,"name":"银川市"},{"id":327,"name":"石嘴山市"},{"id":328,"name":"吴忠市"},{"id":329,"name":"固原市"},{"id":330,"name":"中卫市"}]},{"id":650000,"name":"新疆维吾尔自治区","chindren":[{"id":331,"name":"乌鲁木齐市"},{"id":332,"name":"克拉玛依市"},{"id":333,"name":"吐鲁番地区"},{"id":334,"name":"哈密地区"},{"id":335,"name":"昌吉回族自治州"},{"id":336,"name":"博尔塔拉州"},{"id":337,"name":"巴音郭楞州"},{"id":338,"name":"阿克苏地区"},{"id":339,"name":"克孜勒苏柯尔克孜"},{"id":340,"name":"喀什地区"},{"id":341,"name":"和田地区"},{"id":342,"name":"伊犁哈萨克自治州"},{"id":343,"name":"塔城地区"},{"id":344,"name":"阿勒泰地区"},{"id":345,"name":"省直辖行政单位"},{"id":359,"name":"石河子市"},{"id":384,"name":"阿拉尔市"},{"id":385,"name":"五家渠市"},{"id":386,"name":"图木舒克市"}]},{"id":710000,"name":"台湾省","chindren":[{"id":346,"name":"台湾"},{"id":5170021,"name":"台北市"},{"id":5170024,"name":"新北市"},{"id":5170025,"name":"新竹市"}]},{"id":810000,"name":"香港特别行政区","chindren":[{"id":347,"name":"香港特别行政区"}]},{"id":820000,"name":"澳门特别行政区","chindren":[{"id":348,"name":"澳门特别行政区"}]}],"code":"0","msg":"请求成功"};
var szNbCityData	={"servertime":"2016-03-12 10:09:57","data":[{"id":"","name":"全部","chindren":[]},{"id":999999,"name":"深圳市","chindren":[{"id":99,"name":"深圳"}]},{"id":110000,"name":"北京市","chindren":[{"id":1,"name":"北京"}]},{"id":120000,"name":"天津市","chindren":[{"id":3,"name":"天津"}]},{"id":130000,"name":"河北省","chindren":[{"id":5,"name":"石家庄市"},{"id":6,"name":"唐山市"},{"id":7,"name":"秦皇岛市"},{"id":8,"name":"邯郸市"},{"id":9,"name":"邢台市"},{"id":10,"name":"保定市"},{"id":11,"name":"张家口市"},{"id":12,"name":"承德市"},{"id":13,"name":"沧州市"},{"id":14,"name":"廊坊市"},{"id":15,"name":"衡水市"},{"id":387,"name":"涿州市"},{"id":5170010,"name":"藁城市"},{"id":5170026,"name":"遵化市"}]},{"id":140000,"name":"山西省","chindren":[{"id":16,"name":"太原市"},{"id":17,"name":"大同市"},{"id":18,"name":"阳泉市"},{"id":19,"name":"长治市"},{"id":20,"name":"晋城市"},{"id":21,"name":"朔州市"},{"id":22,"name":"晋中市"},{"id":23,"name":"运城市"},{"id":24,"name":"忻州市"},{"id":25,"name":"临汾市"},{"id":26,"name":"吕梁市"}]},{"id":150000,"name":"内蒙古自治区","chindren":[{"id":27,"name":"呼和浩特市"},{"id":28,"name":"包头市"},{"id":29,"name":"乌海市"},{"id":30,"name":"赤峰市"},{"id":31,"name":"通辽市"},{"id":32,"name":"鄂尔多斯市"},{"id":33,"name":"呼伦贝尔市"},{"id":34,"name":"巴彦淖尔市"},{"id":35,"name":"乌兰察布市"},{"id":36,"name":"兴安盟"},{"id":37,"name":"锡林郭勒盟"},{"id":38,"name":"阿拉善盟"}]},{"id":210000,"name":"辽宁省","chindren":[{"id":39,"name":"沈阳市"},{"id":40,"name":"大连市"},{"id":41,"name":"鞍山市"},{"id":42,"name":"抚顺市"},{"id":43,"name":"本溪市"},{"id":44,"name":"丹东市"},{"id":45,"name":"锦州市"},{"id":46,"name":"营口市"},{"id":47,"name":"阜新市"},{"id":48,"name":"辽阳市"},{"id":49,"name":"盘锦市"},{"id":50,"name":"铁岭市"},{"id":51,"name":"朝阳市"},{"id":52,"name":"葫芦岛市"}]},{"id":220000,"name":"吉林省","chindren":[{"id":53,"name":"长春市"},{"id":54,"name":"吉林市"},{"id":55,"name":"四平市"},{"id":56,"name":"辽源市"},{"id":57,"name":"通化市"},{"id":58,"name":"白山市"},{"id":59,"name":"松原市"},{"id":60,"name":"白城市"},{"id":61,"name":"延边朝鲜族自治州"},{"id":5170008,"name":"德惠市"}]},{"id":230000,"name":"黑龙江省","chindren":[{"id":62,"name":"哈尔滨市"},{"id":63,"name":"齐齐哈尔市"},{"id":64,"name":"鸡西市"},{"id":65,"name":"鹤岗市"},{"id":66,"name":"双鸭山市"},{"id":67,"name":"大庆市"},{"id":68,"name":"伊春市"},{"id":69,"name":"佳木斯市"},{"id":70,"name":"七台河市"},{"id":71,"name":"牡丹江市"},{"id":72,"name":"黑河市"},{"id":73,"name":"绥化市"},{"id":74,"name":"大兴安岭地区"}]},{"id":310000,"name":"上海市","chindren":[{"id":75,"name":"上海"}]},{"id":320000,"name":"江苏省","chindren":[{"id":77,"name":"南京市"},{"id":78,"name":"无锡市"},{"id":79,"name":"徐州市"},{"id":80,"name":"常州市"},{"id":81,"name":"苏州市"},{"id":82,"name":"南通市"},{"id":83,"name":"连云港市"},{"id":84,"name":"淮安市"},{"id":85,"name":"盐城市"},{"id":86,"name":"扬州市"},{"id":87,"name":"镇江市"},{"id":88,"name":"泰州市"},{"id":89,"name":"宿迁市"}]},{"id":330000,"name":"浙江省","chindren":[{"id":90,"name":"杭州市"},{"id":91,"name":"宁波市"},{"id":92,"name":"温州市"},{"id":93,"name":"嘉兴市"},{"id":94,"name":"湖州市"},{"id":95,"name":"绍兴市"},{"id":96,"name":"金华市"},{"id":97,"name":"衢州市"},{"id":98,"name":"舟山市"},{"id":99,"name":"台州市"},{"id":100,"name":"丽水市"}]},{"id":340000,"name":"安徽省","chindren":[{"id":101,"name":"合肥市"},{"id":102,"name":"芜湖市"},{"id":103,"name":"蚌埠市"},{"id":104,"name":"淮南市"},{"id":105,"name":"马鞍山市"},{"id":106,"name":"淮北市"},{"id":107,"name":"铜陵市"},{"id":108,"name":"安庆市"},{"id":109,"name":"黄山市"},{"id":110,"name":"滁州市"},{"id":111,"name":"阜阳市"},{"id":112,"name":"宿州市"},{"id":113,"name":"巢湖市"},{"id":114,"name":"六安市"},{"id":115,"name":"亳州市"},{"id":116,"name":"池州市"},{"id":117,"name":"宣城市"}]},{"id":350000,"name":"福建省","chindren":[{"id":118,"name":"福州市"},{"id":119,"name":"厦门市"},{"id":120,"name":"莆田市"},{"id":121,"name":"三明市"},{"id":122,"name":"泉州市"},{"id":123,"name":"漳州市"},{"id":124,"name":"南平市"},{"id":125,"name":"龙岩市"},{"id":126,"name":"宁德市"}]},{"id":360000,"name":"江西省","chindren":[{"id":127,"name":"南昌市"},{"id":128,"name":"景德镇市"},{"id":129,"name":"萍乡市"},{"id":130,"name":"九江市"},{"id":131,"name":"新余市"},{"id":132,"name":"鹰潭市"},{"id":133,"name":"赣州市"},{"id":134,"name":"吉安市"},{"id":135,"name":"宜春市"},{"id":136,"name":"抚州市"},{"id":137,"name":"上饶市"}]},{"id":370000,"name":"山东省","chindren":[{"id":138,"name":"济南市"},{"id":139,"name":"青岛市"},{"id":140,"name":"淄博市"},{"id":141,"name":"枣庄市"},{"id":142,"name":"东营市"},{"id":143,"name":"烟台市"},{"id":144,"name":"潍坊市"},{"id":145,"name":"济宁市"},{"id":146,"name":"泰安市"},{"id":147,"name":"威海市"},{"id":148,"name":"日照市"},{"id":149,"name":"莱芜市"},{"id":150,"name":"临沂市"},{"id":151,"name":"德州市"},{"id":152,"name":"聊城市"},{"id":153,"name":"滨州市"},{"id":382,"name":"菏泽市"}]},{"id":410000,"name":"河南省","chindren":[{"id":155,"name":"郑州市"},{"id":156,"name":"开封市"},{"id":157,"name":"洛阳市"},{"id":158,"name":"平顶山市"},{"id":159,"name":"安阳市"},{"id":160,"name":"鹤壁市"},{"id":161,"name":"新乡市"},{"id":162,"name":"焦作市"},{"id":163,"name":"濮阳市"},{"id":164,"name":"许昌市"},{"id":165,"name":"漯河市"},{"id":166,"name":"三门峡市"},{"id":167,"name":"南阳市"},{"id":168,"name":"商丘市"},{"id":169,"name":"信阳市"},{"id":170,"name":"周口市"},{"id":171,"name":"驻马店市"},{"id":360,"name":"济源市"}]},{"id":420000,"name":"湖北省","chindren":[{"id":172,"name":"武汉市"},{"id":173,"name":"黄石市"},{"id":174,"name":"十堰市"},{"id":175,"name":"宜昌市"},{"id":176,"name":"襄樊市"},{"id":177,"name":"鄂州市"},{"id":178,"name":"荆门市"},{"id":179,"name":"孝感市"},{"id":180,"name":"荆州市"},{"id":181,"name":"黄冈市"},{"id":182,"name":"咸宁市"},{"id":183,"name":"随州市"},{"id":184,"name":"恩施州"},{"id":185,"name":"省直辖行政单位"},{"id":361,"name":"仙桃市"},{"id":362,"name":"天门市"},{"id":363,"name":"潜江市"},{"id":364,"name":"神农架林区"}]},{"id":430000,"name":"湖南省","chindren":[{"id":186,"name":"长沙市"},{"id":187,"name":"株洲市"},{"id":188,"name":"湘潭市"},{"id":189,"name":"衡阳市"},{"id":190,"name":"邵阳市"},{"id":191,"name":"岳阳市"},{"id":192,"name":"常德市"},{"id":193,"name":"张家界市"},{"id":194,"name":"益阳市"},{"id":195,"name":"郴州市"},{"id":196,"name":"永州市"},{"id":197,"name":"怀化市"},{"id":198,"name":"娄底市"},{"id":199,"name":"湘西土家族苗族自治州"},{"id":5170015,"name":"耒阳市"},{"id":5170017,"name":"韶山市"},{"id":5170018,"name":"吉首市"}]},{"id":440000,"name":"广东省","chindren":[{"id":200,"name":"广州市"},{"id":201,"name":"韶关市"},{"id":203,"name":"珠海市"},{"id":204,"name":"汕头市"},{"id":205,"name":"佛山市"},{"id":206,"name":"江门市"},{"id":207,"name":"湛江市"},{"id":208,"name":"茂名市"},{"id":209,"name":"肇庆市"},{"id":210,"name":"惠州市"},{"id":211,"name":"梅州市"},{"id":212,"name":"汕尾市"},{"id":213,"name":"河源市"},{"id":214,"name":"阳江市"},{"id":215,"name":"清远市"},{"id":216,"name":"东莞市"},{"id":217,"name":"中山市"},{"id":218,"name":"潮州市"},{"id":219,"name":"揭阳市"},{"id":220,"name":"云浮市"}]},{"id":450000,"name":"广西壮族自治区","chindren":[{"id":221,"name":"南宁市"},{"id":222,"name":"柳州市"},{"id":223,"name":"桂林市"},{"id":224,"name":"梧州市"},{"id":225,"name":"北海市"},{"id":226,"name":"防城港市"},{"id":227,"name":"钦州市"},{"id":228,"name":"贵港市"},{"id":229,"name":"玉林市"},{"id":230,"name":"百色市"},{"id":231,"name":"贺州市"},{"id":232,"name":"河池市"},{"id":233,"name":"来宾市"},{"id":234,"name":"崇左市"}]},{"id":460000,"name":"海南省","chindren":[{"id":235,"name":"海口市"},{"id":236,"name":"三亚市"},{"id":237,"name":"省直辖"},{"id":349,"name":"琼海市"},{"id":367,"name":"万宁市"},{"id":368,"name":"五指山市"},{"id":369,"name":"东方市"},{"id":370,"name":"儋州市"},{"id":371,"name":"临高县"},{"id":372,"name":"澄迈县"},{"id":373,"name":"定安县"},{"id":374,"name":"屯昌县"},{"id":375,"name":"昌江黎族自治县"},{"id":376,"name":"琼中县"},{"id":377,"name":"陵水黎族自治县"},{"id":378,"name":"保亭黎族县"},{"id":379,"name":"乐东黎族自治县"},{"id":380,"name":"白沙黎族自治县"},{"id":381,"name":"文昌市"}]},{"id":500000,"name":"重庆市","chindren":[{"id":238,"name":"重庆"}]},{"id":510000,"name":"四川省","chindren":[{"id":241,"name":"成都市"},{"id":242,"name":"自贡市"},{"id":243,"name":"攀枝花市"},{"id":244,"name":"泸州市"},{"id":245,"name":"德阳市"},{"id":246,"name":"绵阳市"},{"id":247,"name":"广元市"},{"id":248,"name":"遂宁市"},{"id":249,"name":"内江市"},{"id":250,"name":"乐山市"},{"id":251,"name":"南充市"},{"id":252,"name":"眉山市"},{"id":253,"name":"宜宾市"},{"id":254,"name":"广安市"},{"id":255,"name":"达州市"},{"id":256,"name":"雅安市"},{"id":257,"name":"巴中市"},{"id":258,"name":"资阳市"},{"id":259,"name":"阿坝州"},{"id":260,"name":"甘孜藏族自治州"},{"id":261,"name":"凉山彝族自治州"}]},{"id":520000,"name":"贵州省","chindren":[{"id":262,"name":"贵阳市"},{"id":263,"name":"六盘水市"},{"id":264,"name":"遵义市"},{"id":265,"name":"安顺市"},{"id":266,"name":"铜仁市"},{"id":267,"name":"黔西南布依族苗族自治州"},{"id":268,"name":"毕节市"},{"id":269,"name":"黔东南苗族侗族自治州"},{"id":270,"name":"黔南布依族苗族自治州"}]},{"id":530000,"name":"云南省","chindren":[{"id":271,"name":"昆明市"},{"id":272,"name":"曲靖市"},{"id":273,"name":"玉溪市"},{"id":274,"name":"保山市"},{"id":275,"name":"昭通市"},{"id":276,"name":"丽江市"},{"id":277,"name":"思茅市"},{"id":278,"name":"临沧市"},{"id":279,"name":"楚雄彝族自治州"},{"id":280,"name":"红河州"},{"id":281,"name":"文山州"},{"id":282,"name":"西双版纳傣族自治州"},{"id":283,"name":"大理白族自治州"},{"id":284,"name":"德宏州"},{"id":285,"name":"怒江傈僳族自治州"},{"id":286,"name":"迪庆藏族自治州"},{"id":350,"name":"普洱市"},{"id":351,"name":"文山州"},{"id":352,"name":"红河州"},{"id":353,"name":"西双版纳傣族自治州"},{"id":354,"name":"楚雄州"},{"id":355,"name":"大理州"},{"id":356,"name":"德宏州"},{"id":357,"name":"怒江州"},{"id":358,"name":"迪庆州"}]},{"id":540000,"name":"西藏自治区","chindren":[{"id":287,"name":"拉萨市"},{"id":288,"name":"昌都地区"},{"id":289,"name":"山南地区"},{"id":290,"name":"日喀则地区"},{"id":291,"name":"那曲地区"},{"id":292,"name":"阿里地区"},{"id":293,"name":"林芝地区"}]},{"id":610000,"name":"陕西省","chindren":[{"id":294,"name":"西安市"},{"id":295,"name":"铜川市"},{"id":296,"name":"宝鸡市"},{"id":297,"name":"咸阳市"},{"id":298,"name":"渭南市"},{"id":299,"name":"延安市"},{"id":300,"name":"汉中市"},{"id":301,"name":"榆林市"},{"id":302,"name":"安康市"},{"id":303,"name":"商洛市"}]},{"id":620000,"name":"甘肃省","chindren":[{"id":304,"name":"兰州市"},{"id":305,"name":"嘉峪关市"},{"id":306,"name":"金昌市"},{"id":307,"name":"白银市"},{"id":308,"name":"天水市"},{"id":309,"name":"武威市"},{"id":310,"name":"张掖市"},{"id":311,"name":"平凉市"},{"id":312,"name":"酒泉市"},{"id":313,"name":"庆阳市"},{"id":314,"name":"定西市"},{"id":315,"name":"陇南市"},{"id":365,"name":"临夏回族自治州"},{"id":366,"name":"甘南藏族自治州"},{"id":383,"name":"武威市"}]},{"id":630000,"name":"青海省","chindren":[{"id":318,"name":"西宁市"},{"id":319,"name":"海东地区"},{"id":320,"name":"海北藏族自治州"},{"id":321,"name":"黄南藏族自治州"},{"id":322,"name":"海南藏族自治州"},{"id":323,"name":"果洛藏族自治州"},{"id":324,"name":"玉树藏族自治州"},{"id":325,"name":"海西州"}]},{"id":640000,"name":"宁夏回族自治区","chindren":[{"id":326,"name":"银川市"},{"id":327,"name":"石嘴山市"},{"id":328,"name":"吴忠市"},{"id":329,"name":"固原市"},{"id":330,"name":"中卫市"}]},{"id":650000,"name":"新疆维吾尔自治区","chindren":[{"id":331,"name":"乌鲁木齐市"},{"id":332,"name":"克拉玛依市"},{"id":333,"name":"吐鲁番地区"},{"id":334,"name":"哈密地区"},{"id":335,"name":"昌吉回族自治州"},{"id":336,"name":"博尔塔拉州"},{"id":337,"name":"巴音郭楞州"},{"id":338,"name":"阿克苏地区"},{"id":339,"name":"克孜勒苏柯尔克孜"},{"id":340,"name":"喀什地区"},{"id":341,"name":"和田地区"},{"id":342,"name":"伊犁哈萨克自治州"},{"id":343,"name":"塔城地区"},{"id":344,"name":"阿勒泰地区"},{"id":345,"name":"省直辖行政单位"},{"id":359,"name":"石河子市"},{"id":384,"name":"阿拉尔市"},{"id":385,"name":"五家渠市"},{"id":386,"name":"图木舒克市"}]},{"id":710000,"name":"台湾省","chindren":[{"id":346,"name":"台湾"},{"id":5170021,"name":"台北市"},{"id":5170024,"name":"新北市"},{"id":5170025,"name":"新竹市"}]},{"id":810000,"name":"香港特别行政区","chindren":[{"id":347,"name":"香港特别行政区"}]},{"id":820000,"name":"澳门特别行政区","chindren":[{"id":348,"name":"澳门特别行政区"}]}],"code":"0","msg":"请求成功"};
var adCityData1 	={"servertime": "2016-03-12 10:09:57","data": [{"id": "","name": "全部","chindren": [{"id": "","name": "全部"}]},{"id": 110000,"name": "北京","chindren": [{"id": 1,"name": "北京"}]},{"id": 120000,"name": "天津","chindren": [{"id": 3,"name": "天津"}]},{"id": 130000,"name": "河北","chindren": [{"id": 5,"name": "石家庄市"},{"id": 5170026,"name": "遵化市"}]},{"id": 140000,"name": "山西","chindren": [{"id": 16,"name": "太原市"},{"id": 26,"name": "吕梁市"}]},{"id": 150000,"name": "内蒙古自治区","chindren": [{"id": 27,"name": "呼和浩特市"},{"id": 38,"name": "阿拉善盟"}]},{"id": 210000,"name": "辽宁","chindren": [{"id": 39, "name": "沈阳市"},{"id": 52,"name": "葫芦岛市"}]},{"id": 220000,"name": "吉林","chindren": [{"id": 53,"name": "长春市"},{"id": 61,"name": "延边朝鲜族自治州"},{"id": 5170008,"name": "德惠市"}]},{"id": 230000,"name": "黑龙江省","chindren": [{"id": 62,"name": "哈尔滨市"},{"id": 74,"name": "大兴安岭地区"}]},{"id": 310000,"name": "上海","chindren": [{"id": 75,"name": "上海"}]},{"id": 320000,"name": "江苏","chindren": [{"id": 77,"name": "南京市"},{"id": 89,"name": "宿迁市"}]},{"id": 330000,"name": "浙江","chindren": [{"id": 90,"name": "杭州市"},{"id": 100,"name": "丽水市"}]},{"id": 340000,"name": "安徽","chindren": [{"id": 101,"name": "合肥市"},{"id": 116,"name": "池州市"},{"id": 117,"name": "宣城市"}]},{"id": 350000,"name": "福建","chindren": [{"id": 118,"name": "福州市"},{"id": 126,"name": "宁德市"}]},{"id": 360000,"name": "江西","chindren": [{"id": 127,"name": "南昌市"},{"id": 137,"name": "上饶市"}]},{"id": 370000,"name": "山东","chindren": [{"id": 138,"name": "济南市"},{"id": 382,"name": "菏泽市"} ]},{"id": 410000,"name": "河南","chindren": [{"id": 155,"name": "郑州市"},{"id": 360,"name": "济源市"}]},{"id": 420000,"name": "湖北","chindren": [{"id": 172,"name": "武汉市"},{"id": 364,"name": "神农架林区"}]},{"id": 430000,"name": "湖南","chindren": [{"id": 186,"name": "长沙市"},{"id": 5170017,"name": "韶山市"}]},{"id": 440000,"name": "广东","chindren": [{"id": 200,"name": "广州市"},{"id": 220,"name": "云浮市"}]},{"id": 450000,"name": "广西","chindren": [{"id": 221,"name": "南宁市"},{"id": 234,"name": "崇左市"}]},{"id": 460000,"name": "海南","chindren": [{"id": 235,"name": "海口市"},{"id": 381,"name": "文昌市"}]},{"id": 500000,"name": "重庆","chindren": [{"id": 238,"name": "重庆"}]},{"id": 510000,"name": "四川","chindren": [{"id": 241,"name": "成都市"},{"id": 260,"name": "甘孜藏族自治州"},{"id": 261,"name": "凉山彝族自治州"}]},{"id": 520000,"name": "贵州","chindren": [{"id": 262,"name": "贵阳市"},{"id": 270,"name": "黔南布依族苗族自治州"}]},{"id": 530000,"name": "云南","chindren": [{"id": 271,"name": "昆明市"},{"id": 358,"name": "迪庆州"}]},{"id": 540000,"name": "西藏自治区","chindren": [{"id": 287,"name": "拉萨市"},{"id": 292,"name": "阿里地区"},{"id": 293,"name": "林芝地区"}]},{"id": 610000,"name": "陕西","chindren": [{"id": 294,"name": "西安市"},{"id": 302,"name": "安康市"},{"id": 303,"name": "商洛市"}]},{"id": 620000,"name": "甘肃","chindren": [{"id": 304,"name": "兰州市"},{"id": 365,"name": "临夏回族自治州"},{"id": 366,"name": "甘南藏族自治州"},{"id": 383,"name": "武威市"}]},{"id": 630000,"name": "青海","chindren": [{"id": 318,"name": "西宁市"},{"id": 325,"name": "海西州"}]},{"id": 640000,"name": "宁夏","chindren": [{"id": 326,"name": "银川市"},{"id": 327,"name": "石嘴山市"},{"id": 328,"name": "吴忠市"},{"id": 329,"name": "固原市"},{"id": 330,"name": "中卫市"}]},{"id": 650000,"name": "新疆维吾尔自治区","chindren": [{"id": 331,"name": "乌鲁木齐市"},{"id": 332,"name": "克拉玛依市"},{"id": 333,"name": "吐鲁番地区"},{"id": 334,"name": "哈密地区"},{"id": 335,"name": "昌吉回族自治州"},{"id": 336,"name": "博尔塔拉州"},{"id": 337,"name": "巴音郭楞州"},{"id": 338,"name": "阿克苏地区"},{"id": 339,"name": "克孜勒苏柯尔克孜"},{"id": 340,"name": "喀什地区"},{"id": 341,"name": "和田地区"},{"id": 342,"name": "伊犁哈萨克自治州"},{"id": 343,"name": "塔城地区"},{"id": 344,"name": "阿勒泰地区"},{"id": 345,"name": "省直辖行政单位"},{"id": 359,"name": "石河子市"},{"id": 384,"name": "阿拉尔市"},{"id": 385,"name": "五家渠市"},{"id": 386,"name": "图木舒克市"}]},{"id": 710000,"name": "台湾","chindren": [{"id": 346,"name": "台湾"},{"id": 5170021,"name": "台北市"},{"id": 5170024,"name": "新北市"},{"id": 5170025,"name": "新竹市"}]},{"id": 810000,"name": "香港","chindren": [{"id": 347,"name": "香港"}]},{"id": 820000,"name": "澳门特别行政区","chindren": [{"id": 348,"name": "澳门特别行政区"}]}],"code": "0","msg": "请求成功"};
;jQuery.extend({
	adLoading:function(dom,type){
		// dom 父级元素，type，页面切换或ajax
		var type = type || "";// page / ajax
		var $dom =  $("body");
		var loadingBox = "loadingBox";
		if(dom == "#content"){
			$dom = $(dom);
			loadingBox = "loadingBox2";
		}
		$dom.addClass("relative");
		if($("#adLoading"+type).length){
			$("#adLoading"+type).remove();
		}
		// $dom.append('<div id="adLoading'+type+'" class="adLoading
		// '+loadingBox+'"><img src="/images/img/loading.gif"
		// class="loadingImg"></div>');
	},
	adLoaded:function(type){
		if(type=="all"){
			$(".adLoading").hide();
		}else{
			var type = type || "";// page / ajax
			$("#adLoading"+type).hide();
		}
		
	}
});
;jQuery.extend({
	adAlert: function(msg,OK,title) {
		if(!$("body").find("#adAlert").length){
			// 添加对话框dom
			$("body").append('<div id="adAlertCover_layer" class="adCover_layer" style="display:none; position:absolute;top:0;left:0;width:100%;height:100%;z-index:9999999;"></div>'+
				'<div  class="Pop_layer" id="adAlert" style="width: 550px;z-index:99999999;display: none;">'+
				'<div class="Pop_layer_box">'+
				'<!-- 弹出框标题 start -->'+
				'<div class="Pop_layer_box_top">'+
				'<span id="adAlertTitle">'+document.title+'</span>'+
				'</div>'+
				'<!-- 弹出框标题 end -->'+
				'<!-- 弹出框内容 start -->'+
				'<div class="Pop_layer_box_center" id="adPop_context">'+
				'<div id="adAlertMsg" style="font-size:22px;line-height:25px;"></div>'+
				'</div>'+
				'<!-- 弹出框内容 end -->'+
				'<!-- 弹出框按钮 start -->'+
				'<div class="Pop_layer_box_bottom">'+
				'<div>'+
				'<input id="adAlertOKBtn" class="Pop_layer_box_bottom_btn" type="button" value="确定" /> '+
				'<input id="adAlertCancelBtn" class=" Pop_layer_box_bottom_btn" type="button" value="取消" /> '+
				'</div>'+
				'</div>'+
				'</div>'+
				'</div>'
			);
			
			$("#adAlertCancelBtn").unbind("click").bind("click",function(){
				$("#adAlertCover_layer").hide();
				$("#adAlert").fadeOut();
			});
		}
		$("#adAlertTitle").html(title||document.title)
		if($("#adAlert").is(':visible'))return;
		$("#adAlertOKBtn").unbind("click").on("click",function(){
			OK && OK();
			$("#adAlertCover_layer").hide();
			$("#adAlert").fadeOut();
		});
		$("#adAlertCover_layer").show();
		$('#adAlert').css('position', "absolute");
		$('#adAlert').css('left', $("body").width() / 2 - ($('#adAlert').width() / 2));
		$('#adAlert').css('top', "188px");
		$("#adAlert").fadeIn();
		if($("body").height()<$("#adAlert").height()){
			$("body").css("min-height",$('#adAlert').height()+50);
		}
		$("#adAlertMsg").html(msg);
		$("#adAlertMsg").css('padding-top',($("#adPop_context").height() - $("#adAlertMsg").height())/2 + 10);
	},
	/*
	 * $.adAjax({ type:"POST", //默认是get data:{}, // Json字符串 url:"abc/ad", //
	 * 请求url success:function(){} //成功方法 })
	 */ 
	adAjax:function(obj){
		$.ajaxSetup({ cache: false }); 
		$.adLoading("#content","ajax");
		$.ajax({
			type 	: obj.type || "GET",
			timeout : 3000, // 超时时间设置，单位毫秒
			url 	: window.location.origin + obj.url 	|| "",
			data 	: obj.data || {},
			dataType: "text", 
			async : false,
			success : function(data) {
				var data = eval('('+data+')');
				if(data.resultCode == "001"){
					location.href="/login.html";
				}
				if (obj.success && $.isFunction(obj.success)){
					obj.success(data);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				$.adLoaded("ajax");
				if (obj.error && $.isFunction(obj.error)){
					obj.error(XMLHttpRequest, textStatus, errorThrown);
				}
			},
		    complete : function(XMLHttpRequest,status){ // 请求完成后最终执行参数
			    $.adLoaded("ajax");
		        if(status=='timeout'){// 超时,status还有success,error等值的情况
			        // ajaxTimeoutTest.abort();
				    $.alert && $.alert("您的网络不通畅，请检查网络后,刷新页面再试！");
				    $.alert || alert("您的网络不通畅，请检查网络后,刷新页面再试！");
		        }
		  }
		});
	},
	adCityChange:function(city,area,type,szType){
		var b = szType ? szNbCityData : adCityData;
		// 没有正常change的返回值
		var returnData = "";
		if(area == "code" || area == "city"){
			type = area;
			area = null;
		}
		if(area && !city.toString().split("-")[1]){
			returnData = city + "-" + area;
		}else{
			returnData = city;
		}
		
		// 根据type判断是否需要转换
		if(parseInt(city.split("-"))>0 && type=="code" 
			|| !parseInt(city.split("-"))>0 && type=="city"
		){
			return returnData;
		}
        if(!city){
        	// 无城市数据
        	return returnData;
        }else if(!city.toString().split("-")[1] && !area && +city.toString().split("-")[0]>0 && +city.toString().split("-")[0]<1000){
        	// 无区城市数据 city 是 area
        	return changeArea(city.toString());
        }else if(!city.toString().split("-")[1] && !area){
			return changeCity(city.toString());
        }else{
        	return changeCityArea(city.toString().split("-")[0],city.toString().split("-")[1]||area);
        }
        function changeCity(city){
        	for(var i in b.data){
	        	if(b.data[i].id == city){
	        		return b.data[i].name;
	        	}
	        	if(b.data[i].name == city){
	        		return b.data[i].id;
	        	}
	        }
	        return returnData;
        }
        function changeArea(area){
        	for(var i in b.data){
        		// 数字
        		for(var j in b.data[i].chindren){
        			if(b.data[i].chindren[j].id == area){
        				return b.data[i].chindren[j].name;
        			}
        		}
        		// 名称
        		for(var j in b.data[i].chindren){
        			if(b.data[i].chindren[j].name == area){
        				return b.data[i].chindren[j].id;
        			}
        		}
	        }
	        return returnData;
        }
        function changeCityArea(city,area){
        	for(var i in b.data){
        		// 数字
	        	if(b.data[i].id == city){
	        		for(var j in b.data[i].chindren){
	        			if(b.data[i].chindren[j].id == area){
	        				return b.data[i].name+"-"+b.data[i].chindren[j].name;
	        			}
	        		}
	        	}
	        	// 名称
	        	if(b.data[i].name == city){
	        		for(var j in b.data[i].chindren){
	        			if(b.data[i].chindren[j].name == area){
	        				return b.data[i].id+"-"+b.data[i].chindren[j].id;
	        			}
	        		}
	        	}
	        }
	        return returnData;
        }
        
	},
	adGetProvince:function(city,area,type){
		// 没有正常change的返回值
		var returnData = "";
		if(area == "code" || area == "city"){
			type = area;
			area = null;
		}
		if(area && !city.toString().split("-")[1]){
			returnData = city + "-" + area;
		}else{
			returnData = city;
		}
		
		// 根据type判断是否需要转换
		if(parseInt(city.split("-"))>0 && type=="code" 
			|| !parseInt(city.split("-"))>0 && type=="city"
		){
			return returnData;
		}
		var b=adCityData1;
		if(!city){
        	// 无城市数据
        	return returnData;
        }else if(!city.toString().split("-")[1] && !area && +city.toString().split("-")[0]>0 && +city.toString().split("-")[0]<1000){
        	// 无区城市数据 city 是 area
        	return changeArea(city.toString());
        }else if(!city.toString().split("-")[1] && !area){
			return changeCity(city.toString());
        }else{
        	return changeCityArea(city.toString().split("-")[0],city.toString().split("-")[1]||area);
        }
        function changeCity(city){
        	for(var i in b.data){
	        	if(b.data[i].id == city){
	        		return b.data[i].name;
	        	}
	        	if(b.data[i].name == city){
	        		return b.data[i].id;
	        	}
	        }
	        return returnData;
        }
        function changeArea(area){
        	for(var i in b.data){
        		// 数字
        		for(var j in b.data[i].chindren){
        			if(b.data[i].chindren[j].id == area){
        				return b.data[i].chindren[j].name;
        			}
        		}
        		// 名称
        		for(var j in b.data[i].chindren){
        			if(b.data[i].chindren[j].name == area){
        				return b.data[i].chindren[j].id;
        			}
        		}
	        }
	        return returnData;
        }
        function changeCityArea(city,area){
        	for(var i in b.data){
        		// 数字
	        	if(b.data[i].id == city){
	        		for(var j in b.data[i].chindren){
	        			if(b.data[i].chindren[j].id == area){
	        				return b.data[i].name+"-"+b.data[i].chindren[j].name;
	        			}
	        		}
	        	}
	        	// 名称
	        	if(b.data[i].name == city){
	        		for(var j in b.data[i].chindren){
	        			if(b.data[i].chindren[j].name == area){
	        				return b.data[i].id+"-"+b.data[i].chindren[j].id;
	        			}
	        		}
	        	}
	        }
	        return returnData;
        }
        
	},
	adHref:function(href,data){
		window.adSaveData = data || {};
		if(href){
			window.location.href = href;
		}
	},
	getRealLength:function(str) {
	    ///<summary>获得字符串实际长度，中文2，英文1</summary>

	    var realLength = 0, len = str.length, charCode = -1;
	    for (var i = 0; i < len; i++) {
	        charCode = str.charCodeAt(i);
	        if (charCode >= 0 && charCode <= 128) realLength += 1;
	        else realLength += 2;
	    }
	    return realLength;
	},
	setHeadMenu:function(menuId){
		var menuId = menuId || "#menu2";
		var $pDom =  $(menuId);
		var $dom = $(".z-crt").find(".js-headMenu-nav-icon");
			$(".z-crt").removeClass("z-crt");
			iconActive($dom,false);
			$pDom.addClass("z-crt");
			var $dom2 = $(".z-crt").find(".js-headMenu-nav-icon");
			iconActive($dom2,true);
		function iconActive($dom,bool){
			var domSrc = $dom.attr("src");
			if(!domSrc)return;
			if(!domSrc.split("active")[1] && bool){
				var newSrc = domSrc.split(".png")[0] + "-active.png";
				$dom.attr("src",newSrc);
			}else if(domSrc.split("active")[1] && !bool && !$dom.parent().parent(".z-crt").length){
				var newSrc = domSrc.split("-active.png")[0] + ".png";
				$dom.attr("src",newSrc);
			}
		}
	},
	adNoScroll:function(bool){
		if(bool){
			$(document.body).addClass("html-body-overflow");
		}else{
			$(document.body).removeClass("html-body-overflow");
		}	
	},
	adReg:function(){
    	$(":input").on("keypress", function(e){
		    checkChar(e.originalEvent);
		})
		function checkChar(e){
		    e = window.event || e;
		    var code = e.keyCode || e.which;
		    var reg = /[\\\/:\*\?"\<\>\|]/;// 禁止 \/:*?"<>|
		    if(reg.test(String.fromCharCode(code))){
		        if(window.event){
		            e.returnValue = false;
		        }else{
		            e.preventDefault();
		        }
		    }
		}
    },
    adIsIE:function(){
    	return !!window.ActiveXObject || "ActiveXObject" in window;
    },
    adIsIE6:function(){
    	return !$.support.leadingWhitespace;
    }

});



;(function($) {

	// 扩展这个方法到jquery
    $.fn.extend({

        // 插件名字
        adValidateOld: function(options) {
        	// 设置默认值并用逗号隔开
            var defaults = {  
            } 
                  
            var options =  $.extend(defaults, options);

            // 遍历匹配元素的集合
            return this.each(function() {  
                var o = options;  
                var $this = $($(this)[0]);
                document.getElementById("adValidate").onblur=function(){
                // $this.on("blur", function(){
                	// 如果是错误状态 查找下一个元素，
                	if($this.hasClass('input-error')){
                		$this
	            			.removeClass('input-error')
	            			.next(".adValidate-msg").empty();
	            	}
                	if (!o.reg.test($this.val())){
                		$this
            			.addClass('input-error')
                		.after('<div class="adValidate-msg sui-msg msg-error help-inline"><div class="msg-con"><span>'+o.errMsg+'</span></div><i class="msg-icon"></i></div>')
                		;  
	            	}
                };
            });  
        },
        adValidate:function(options){
        	$(this).find("[data-rules]").blur();
        	return $(this).find("[data-rules]").hasClass("input-error");
        },
        adSelectVal:function(value,name){
			if(!value || !name)return;
			$(this).parent().find("input").val(value);
			$(this).parent().find("span").html(name);
		},
        adCitySet:function(name,code,szType){
        	if(!name || !code)return;
        	var self = this;
        	var b = szType ? szNbCityData : adCityData;
        	var name = name  || "省份-城市";
        	var code = code  || "-";
        	var nameArray = name.split("-");
        	var codeArray = code.split("-");
        	var parentDom = self.find(".caret").next("span");
        	var childrenDom = self.find("input");
        	var childrenDomUl = self.find("ul");
        	var len = parentDom.length;
        	$(parentDom[0]).html(nameArray[0]);
        	$(childrenDom[0]).val(codeArray[0]);
        	if(!name.split("-")[1])return;
        	showSubCity(codeArray[0]);
        	$(parentDom[1]).html(nameArray[1]);
        	$(childrenDom[1]).val(codeArray[1]);
        	function showSubCity(subVal){
		    	var chindren = {};
		    	var len = b.data.length;
				for(var j = 0; j < len ; j++){
					if(b.data[j].id == subVal){
						chindren = b.data[j].chindren;
					}
				}
				var chindrenHtml = '<li role="presentation"><a role="menuitem" tabindex="-1" href="javascript:void(0);" value="0">全部</a></li>';
		    	for(var k in chindren){
			      chindrenHtml+='<li role="presentation"><a role="menuitem" tabindex="-1" href="javascript:void(0);" value="'+chindren[k].id+'">'+chindren[k].name+'</a></li>'
				}
			    $(childrenDomUl[1]).html("");
			    $(childrenDomUl[1]).html(chindrenHtml);
		    }
        },
        
        adCitySelect:function(options,szType){
        	var $this = $(this);
            // 设置默认值并用逗号隔开
            var b = szType ? szNbCityData : adCityData;
            var defaults = {  
            	hasArea:true,
            	cityId:"adCitySelect",
            	areaId:"adCitySubSelect",
            	disp:"-",
            	defCity:[],
            	needCheck:false,
            	selectFun:function(){}
            } 
            var options =  $.extend(defaults, options);
            var dropdownCity = "dropdown";
            var dropdownArea = "dropdown";
            if(options.disp.split("-")[0] == "city"){
            	dropdownCity = "";
            }
            if(options.disp.split("-")[1] == "area"){
            	dropdownArea = "";
            }
            var requiredCity,requiredArea = "";
            if(options.needCheck){
            	if(options.hasArea){
            		requiredArea = ' data-rules="required" data-empty-msg="请选择" ';
            	}else{
            		requiredCity = ' data-rules="required" data-empty-msg="请选择" ';
            	}
            	
            }
            var html = '<span class="sui-dropdown dropdown-bordered select"><span class="dropdown-inner"><a role="button" href="javascript:void(0);" data-toggle="'+dropdownCity+'" class="dropdown-toggle  input-xfat">'
		      +'<input value="" id="'+options.cityId+'" '+requiredCity+' type="hidden"><i class="caret"></i><span id="'+options.cityId+'-s">省份</span></a>'
		    +'<ul role="menu" aria-labelledby="drop4" class="sui-dropdown-menu">';
	    	for(var i in b.data){
		      html+='<li role="presentation"><a role="menuitem" tabindex="-1" value="'+b.data[i].id+'">'+b.data[i].name+'</a></li>'
			}
		    html+='</ul></span></span>';

		    $this.html(html);
		    // 修改默认值
		    // 城市
		    if($(this).data("city") && $(this).data("city").split(",")[0] && $(this).data("city").split(",")[1]){
		    	$("#"+options.cityId).val($(this).data("city").split(",")[0]);
		    	$("#"+options.cityId).siblings("span").html($(this).data("city").split(",")[1])
		    }
		    setDefCity(options);
		    if(options.hasArea){
		    	var subVal = '';
		    	subVal = $("#"+options.cityId).val();
			    showSubCity(subVal);
			    $("#"+options.cityId).on("change",function(){
			    	subVal = $(this).val();
			    	showSubCity(subVal);
			    });
		    }else{
		    	$("#"+options.cityId).on("change",function(){
			    	options.selectFun();
			    });
		    }
		    
		    // 区县
		    if($(this).data("area") && $(this).data("area").split(",")[0] && $(this).data("area").split(",")[1]){
		    	$("#"+options.areaId).val($(this).data("area").split(",")[0]);
		    	$("#"+options.areaId).siblings("span").html($(this).data("area").split(",")[1])
		    }

		    function showSubCity(subVal){
		    	var chindren = {};
				for(var j in b.data){
					if(b.data[j].id == subVal){
						chindren = b.data[j].chindren;
					}
				}
				var chindrenHtml = '<span class="dropdown-inner"><a role="button" href="javascript:void(0);" data-toggle="'+dropdownArea+'" class="dropdown-toggles  input-xfat">'
			      +'<input value="" id="'+options.areaId+'" '+requiredArea+' type="hidden"><i class="caret"></i><span  id="'+options.areaId+'-s">城市</span></a>'
			    +'<ul role="menu" aria-labelledby="drop4" class="sui-dropdown-menu"><li role="presentation"><a role="menuitem" tabindex="-1" href="javascript:void(0);" value="0">全部</a></li>';
		    	for(var k in chindren){
			      chindrenHtml+='<li role="presentation"><a role="menuitem" tabindex="-1" href="javascript:void(0);" value="'+chindren[k].id+'">'+chindren[k].name+'</a></li>'
				}
			    chindrenHtml+='</ul></span>';
			    if($this.children('.js-ad-adCitySubSelect').length){
			    	$this.children('.js-ad-adCitySubSelect').html(chindrenHtml);
			    }else{
			    	 $this.append('<span class="sui-dropdown js-ad-adCitySubSelect dropdown-bordered select">'+chindrenHtml+"</span>");
			    }
			    $("#"+options.areaId).on("change",function(){
	                options.selectFun();
	            })
		    }
		    function setDefCity(options){
		    	var $cityDom = $("#"+options.cityId).parent().parent().find("ul");
		    	if(options.defCity.length){
		    		var subLisHtml ="";
		    		for(var i in options.defCity){
		    			var $html = $cityDom.find("li a[value="+options.defCity[i]+"]");
		    			if($html.length){
		    				subLisHtml += "<li role='presentation'>"+$html[0].outerHTML+"</li>";
		    			}
		    		}
					$cityDom.html(subLisHtml);
			    }
		    }
		    
        },
        adSelectVal:function(val){
        	// 下拉框设值
        	if(!val)return $(this).val();
        	var $this = $(this);
        	$this.val(val);
        	var txt = $this.parent().parent().find("ul").find("[value='"+val+"']").html();
        	if(!txt)return false;
        	$this.parent().find("span").html(txt);
        },
        adSelectFindTxt:function(val){
        	var $this = $(this);
        	var txt = $this.parent().parent().find("ul").find("[value='"+val+"']").html();
        	return txt;
        },
        adSelectCheck:function(){

        },
        initBank:function(options){
        	var devid = "bankSelect";
        	var inputid = "bankid";
        	var spanid = "bankName";
        	if(options != null){
        		devid = options.devid;
        		inputid = options.inputid;
        		spanid = options.spanid;
        	}
        	var $this = $(this);
            // 设置默认值并用逗号隔开
            var defaults = {
            } 
            var options =  $.extend(defaults, options);
        	var bankSelectHtml = "";
        	var bankList = [{"bankName":"中国工商银行","bankCode":"10001"},{"bankName":"中国农业银行","bankCode":"10002"},{"bankName":"中国银行","bankCode":"10003"},{"bankName":"中国建设银行","bankCode":"10004"},{"bankName":"平安银行","bankCode":"10005"},{"bankName":"交通银行","bankCode":"10006"},{"bankName":"中信银行","bankCode":"10007"},{"bankName":"兴业银行","bankCode":"10008"},{"bankName":"光大银行","bankCode":"10009"},{"bankName":"中国民生银行","bankCode":"10010"},{"bankName":"中国邮政储蓄银行","bankCode":"10011"},{"bankName":"华夏银行","bankCode":"10012"},{"bankName":"招商银行","bankCode":"10013"},{"bankName":"广发银行","bankCode":"10014"},{"bankName":"上海浦东发展银行","bankCode":"10015"},{"bankName":"汇丰银行","bankCode":"10016"},{"bankName":"渣打银行","bankCode":"10017"},{"bankName":"农商银行","bankCode":"10018"},{"bankName":"济宁银行","bankCode":"10019"},{"bankName":"齐鲁银行","bankCode":"10020"},{"bankName":"农村信用合作联社","bankCode":"10021"},{"bankName":"东营银行股份有限公司","bankCode":"10022"},{"bankName":"东莞银行","bankCode":"10023"},{"bankName":"莱商银行","bankCode":"10024"},{"bankName":"东亚银行","bankCode":"10025"},{"bankName":"华润银行","bankCode":"10026"},{"bankName":"重庆银行","bankCode":"10027"},{"bankName":"桂林银行","bankCode":"10028"},{"bankName":"北部湾银行","bankCode":"10029"},{"bankName":"泰安银行","bankCode":"10030"},{"bankName":"民泰商业银行","bankCode":"10031"},{"bankName":"上海银行","bankCode":"10032"},{"bankName":"圣泰农业合作银行","bankCode":"10033"},{"bankName":"紫金农商银行","bankCode":"10034"},{"bankName":"贵阳银行","bankCode":"10035"},{"bankName":"莱芜珠江村镇银行股份有限公司","bankCode":"10036"},{"bankName":"广州银行","bankCode":"10037"},{"bankName":"重庆北碚稠州村镇银行","bankCode":"10038"},{"bankName":"宁夏银行","bankCode":"10039"},{"bankName":"长沙银行股份有限公司","bankCode":"10040"},{"bankName":"临商银行股份有限公司","bankCode":"10041"},{"bankName":"广州市商业银行","bankCode":"10042"},{"bankName":"烟台银行","bankCode":"10043"},{"bankName":"贵州银行股份有限公司","bankCode":"10044"},{"bankName":"长沙银行股份有限公司","bankCode":"10045"},{"bankName":"东源泰业村镇银行","bankCode":"10046"},{"bankName":"杭州银行","bankCode":"10047"},{"bankName":"东营银行","bankCode":"10048"},{"bankName":"长沙银行","bankCode":"10049"},{"bankName":"贵州银行","bankCode":"10050"},{"bankName":"农村信用合作联社","bankCode":"10051"},{"bankName":"江苏银行","bankCode":"10053"},{"bankName":"枣庄银行","bankCode":"10054"},{"bankName":"青岛银行","bankCode":"10055"},{"bankName":"威海银行","bankCode":"10056"},{"bankName":"南充市商业银行","bankCode":"10057"},{"bankName":"赣州银行","bankCode":"10058"},{"bankName":"华兴银行","bankCode":"10059"},{"bankName":"德州银行","bankCode":"10060"},{"bankName":"柳州银行","bankCode":"10061"},{"bankName":"浙商银行","bankCode":"10062"},{"bankName":"成都银行","bankCode":"10063"},{"bankName":"兰州银行","bankCode":"10064"},{"bankName":"北京银行","bankCode":"10065"},{"bankName":"大连银行股份有限公司","bankCode":"10066"},{"bankName":"乌鲁木齐银行","bankCode":"10067"},{"bankName":"深圳农村商业银行","bankCode":"10068"},{"bankName":"宁波银行","bankCode":"10069"},{"bankName":"浙江稠州商业银行","bankCode":"10070"},{"bankName":"昆仑银行","bankCode":"10071"},{"bankName":"德阳银行","bankCode":"10072"},{"bankName":"宜宾市商业银行","bankCode":"10073"},{"bankName":"哈尔滨银行","bankCode":"10074"},{"bankName":"锦州银行","bankCode":"10075"},{"bankName":"韩亚银行","bankCode":"10076"},{"bankName":"盛京银行","bankCode":"10077"},{"bankName":"温州银行","bankCode":"10078"},{"bankName":"台州银行","bankCode":"10079"},{"bankName":"恒丰银行","bankCode":"10080"},{"bankName":"农村银行","bankCode":"10081"},{"bankName":"广东南粤银行","bankCode":"10082"},{"bankName":"新疆天山农村商业银行","bankCode":"10083"},{"bankName":"新疆昌吉农村商业银行","bankCode":"10084"},{"bankName":"新疆汇和银行","bankCode":"10085"},{"bankName":"威海市商业银行","bankCode":"10086"},{"bankName":"丹东银行股份有限公司","bankCode":"10087"},{"bankName":"重庆三峡银行","bankCode":"10088"},{"bankName":"江西银行","bankCode":"10089"},{"bankName":"赣州银座村镇银行","bankCode":"10090"},{"bankName":"微商银行股份有限公司","bankCode":"10091"},{"bankName":"其他银行","bankCode":"10092"}];
        	bankSelectHtml += "<span class='dropdown-inner'><a id='select' role='button' href='javascript:void(0);' data-toggle='dropdown' class='ad-widget167 input-xfat dropdown-toggle'>";
        	bankSelectHtml += "<input id='"+inputid+"' value='' name='"+inputid+"' data-rules='required' data-empty-msg='请选择开户行' type='hidden' /><i class='caret'></i><span id='"+spanid+"'>请选择开户行</span></a>";
        	bankSelectHtml += "<ul role='menu' aria-labelledby='drop1' class='sui-dropdown-menu'>";
        	for(var i in bankList){
        		bankSelectHtml += "<li role='presentation'><a role='menuitem' tabindex='-1' href='javascript:void(0);' value='"+bankList[i].bankCode+"'>"+bankList[i].bankName+"</a></li>";
        	}
        	bankSelectHtml += "</ul></span>";
        	$("#"+devid).append(bankSelectHtml);
        },
        adContenTip:function(speed){
        	var speed = speed || 12;
        	var $a = $(this);
            var width = $a.width();
            var parentWidth = $a.parent().width();
            var l = parentWidth;
            window.adContentInterval && clearInterval(window.adContentInterval);
            window.adContentInterval = setInterval(function(){
                l = moveLeft(l,$a);
            },speed*3);
            function moveLeft(l,$a){
                if (l <= -$a.width()){
                    l = $a.parent().width();
                }
                $a.css("left", l);
                l -= 3;
                return l;
            }
            $a.unbind("mouseover").bind("mouseover",function(){
                clearInterval(window.adContentInterval);
            })
            $a.unbind("mouseout").bind("mouseout",function(){
                window.adContentInterval = setInterval(function(){
                    l = moveLeft(l,$a);
                },speed*3);
            })
        },
        adBigImg:function(){
        	if(!$(this).attr("src"))return;
        	var showIng = false;
        	$(this).on("click",function(evt){
        		if(showIng)return;
        		showIng = true;
                var src = evt.currentTarget.currentSrc;
                var html = '<img id="expImg" class="expImg" src="'+src+'" style="width:800px; height:800px; margin:0px 0px 0px 40px;"><button id="cropBeginBtn" style="margin:10px 0px 0px 40px; border:1px #33affc solid; background-color:#4cb9fc; color:#fff; height:30px;">点击旋转</button> ';
                $.confirm({
                    body: html  // 必填，对话框内容html，可加载temp模板，也可以传值进去
                    // 以下均为可选
                    ,title:'查看大图'
                    ,okBtn : '确定'
                    ,cancelBtn : false  // 默认 取消，false为无取消按钮
                    ,width: '900'    // width:
										// {number|string(px)|'small'|'normal'|'large'}推荐优先使用后三个描述性字符串，统一样式
                    ,height: '550'            // height: {number|string(px)}
												// 内容区（.modal-body）高度
                    ,backdrop: 'static' // 决定是否为模态对话框添加一个背景遮罩层。另外，该属性指定static时，表示添加遮罩层，同时点击模态对话框的外部区域不会将其关闭。
                    ,bgcolor: '#123456' // 背景遮罩层颜色，默认是黑色。可以用rgba设置透明度
                    ,keyboard: true     // 是否可由esc按键关闭
                    ,closeBtn: true     // true/false
										// 是否显示右上角叉叉（html使用方式无需配置，直接改html结构即可）
                    ,hasfoot: true      // true/false 是否显示确定、取消按钮
                    // remote: {string} 如果提供了远程url地址，就会加载远端内容
                    // timeout: {number} 1000 单位毫秒ms ,对话框打开后多久自动关闭
                    // 回调函数
                    ,show: function(){
                    	$.adNoScroll(true)

						var deg = 0;
			            document.getElementById("cropBeginBtn").onclick = function () {
			                deg += 90;
			                document.getElementById("expImg").style.transform = "rotate(" + deg + "deg)";
			            }

                    }
                    ,hide: function(){
                    	$.adNoScroll(false)
                    	showIng = false;
                    }
                })
            })
        },

        initIndustry:function(options,isShowAll){
        	var devid = "industrySelect";
        	var inputid = "industryid";
        	var spanid = "industryName";
        	//var isShowAll = "0"; // 0:需要"全部"   1:不需要全部
        	if(isShowAll == null){
        		isShowAll = "0";
        	}
        	if(options != null){
        		devid = options.devid;
        		inputid = options.inputid;
        		spanid = options.spanid;
        	}
        	var $this = $(this);
            // 设置默认值并用逗号隔开
            var defaults = {  
            } ;
            var options =  $.extend(defaults, options);
        	var industrySelectHtml = "";
        	var industryList = "";
        	if(isShowAll == "1"){
        		industryList = [{"industryName":"红木","industryCode":"1"},{"industryName":"珠宝","industryCode":"2"},{"industryName":"装修","industryCode":"3"},{"industryName":"汽车","industryCode":"4"},{"industryName":"家具","industryCode":"5"},{"industryName":"其它","industryCode":"0"}];
        	}else{
        		industryList = [{"industryName":"全部","industryCode":""},{"industryName":"红木","industryCode":"1"},{"industryName":"珠宝","industryCode":"2"},{"industryName":"装修","industryCode":"3"},{"industryName":"汽车","industryCode":"4"},{"industryName":"家具","industryCode":"5"},{"industryName":"其它","industryCode":"0"}];
        	}
        	
        	industrySelectHtml += "<span class='dropdown-inner'><a id='select' role='button' href='javascript:void(0);' data-toggle='dropdown' class='ad-widget167 input-xfat dropdown-toggle'>";
        	industrySelectHtml += "<input id='"+inputid+"' value='' name='"+inputid+"' data-rules='required' data-empty-msg='请选择行业类别' type='hidden' /><i class='caret'></i><span id='"+spanid+"'>请选择</span></a>";
        	industrySelectHtml += "<ul role='menu' aria-labelledby='drop1' class='sui-dropdown-menu'>";
        	for(var i in industryList){
        		industrySelectHtml += "<li role='presentation'><a role='menuitem' tabindex='-1' href='javascript:void(0);' value='"+industryList[i].industryCode+"'>"+industryList[i].industryName+"</a></li>";
        	}
        	industrySelectHtml += "</ul></span>";
        	$("#"+devid).append(industrySelectHtml);
        }
    });
})(jQuery); 
/**
 * 金额按千位逗号分割
 * 
 * @character_set UTF-8
 * @author Jerry.li(hzjerry@gmail.com)
 * @version 1.2014.08.24.2143 Example <code> 
 *      alert($.formatMoney(1234.345, 2)); //=>1,234.35 
 *      alert($.formatMoney(-1234.345, 2)); //=>-1,234.35 
 *      alert($.unformatMoney(1,234.345)); //=>1234.35 
 *      alert($.unformatMoney(-1,234.345)); //=>-1234.35 
 *  </code>
 */  
;(function($)  
{  
    $.extend({  
        /**
		 * 数字千分位格式化
		 * 
		 * @public
		 * @param mixed
		 *            mVal 数值
		 * @param int
		 *            iAccuracy 小数位精度(默认为2)
		 * @return string
		 */  
        adMoney:function(mVal, iAccuracy){  
            var fTmp = 0.00;// 临时变量
            var iFra = 0;// 小数部分
            var iInt = 0;// 整数部分
            var aBuf = new Array(); // 输出缓存
            var bPositive = true; // 保存正负值标记(true:正数)
            /**
			 * 输出定长字符串，不够补0
			 * <li>闭包函数</li>
			 * 
			 * @param int
			 *            iVal 值
			 * @param int
			 *            iLen 输出的长度
			 */  
            function funZero(iVal, iLen){  
                var sTmp = iVal.toString();  
                var sBuf = new Array();  
                for(var i=0,iLoop=iLen-sTmp.length; i<iLoop; i++)  
                    sBuf.push('0');  
                sBuf.push(sTmp);  
                return sBuf.join('');  
            };  
  
            if (typeof(iAccuracy) === 'undefined')  
                iAccuracy = 2;  
            bPositive = (mVal >= 0);// 取出正负号
            fTmp = (isNaN(fTmp = parseFloat(mVal))) ? 0 : Math.abs(fTmp);// 强制转换为绝对值数浮点
            // 所有内容用正数规则处理
            iInt = parseInt(fTmp); // 分离整数部分
            iFra = parseInt((fTmp - iInt) * Math.pow(10,iAccuracy) + 0.5); // 分离小数部分(四舍五入)
  
            do{  
                aBuf.unshift(funZero(iInt % 1000, 3));  
            }while((iInt = parseInt(iInt/1000)));  
            aBuf[0] = parseInt(aBuf[0]).toString();// 最高段区去掉前导0
            return ((bPositive)?'':'-') + aBuf.join(',') +'.'+ ((0 === iFra)?'00':funZero(iFra, iAccuracy));  
        },  
        /**
		 * 将千分位格式的数字字符串转换为浮点数
		 * 
		 * @public
		 * @param string
		 *            sVal 数值字符串
		 * @return float
		 */  
        adUnMoney:function(sVal){  
            var fTmp = parseFloat(sVal.replace(/,/g, ''));  
            return (isNaN(fTmp) ? 0 : fTmp);  
        },  
    }); 
})(jQuery);
(function(){
	// origin兼容ie
	if (window["context"] == undefined) {
	    if (!window.location.origin) {
	        window.location.origin = window.location.protocol + "//" + window.location.hostname + (window.location.port ? ':' + window.location.port: '');
	    }
	    window["context"] = location.origin+"/V6.0";
	}
	window.url = window.location.protocol + "//" + window.location.hostname;
	window.filePath = "";
	// ie 提示
	if($.adIsIE() && !$(".ieTip").length){
		$("#ad-app").prepend('<div class="ieTip">您的浏览器版本过低，仅能使用部分功能，使用更多功能请升级到最新版本。或使用其他浏览器。</div>');
	}
})();




		  
