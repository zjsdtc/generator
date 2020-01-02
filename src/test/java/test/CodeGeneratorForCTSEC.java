package test;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.*;

/**
 * mybatis代码生成器
 */
public class CodeGeneratorForCTSEC {

    private static final String OUT_PUT_DIR = "E:\\code";//System.getProperty("user.dir")+"/src/main/java";
    private static final String[] TABLES = new String[]{"CTZQ_APP_INFO"};
    private static final String PARENT_PACKAGE = "com.ctsec.dzsw.aps";//父包名
    private static final String[] TABLE_PRE_FIX = new String[]{"CTZQ_"};//表前缀


    private static final String DS_URL = "jdbc:oracle:thin:@172.88.5.107:1521:orcl";
    private static final String DS_USERNAME = "dzswsec";
    private static final String DS_PASSWORD = "dzsw11";

  /**
     * 读取控制台内容
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(OUT_PUT_DIR);//生成文件的输出目录
        gc.setAuthor("zhaijiang");//开发人员
        gc.setOpen(true);//是否打开输出目录
        gc.setServiceName("%sService");//service 命名方式
        gc.setServiceImplName("%sServiceImpl");//service impl 命名方式
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setFileOverride(true);
        gc.setActiveRecord(false);
        gc.setSwagger2(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        //gc.setIdType(IdType.ID_WORKER);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DS_URL);
        // dsc.setSchemaName("public"); 数据库 schema name
        dsc.setDriverName("oracle.jdbc.driver.OracleDriver");
        dsc.setUsername(DS_USERNAME);
        dsc.setPassword(DS_PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));//父包模块名
        pc.setParent(PARENT_PACKAGE);//父包名。// 自定义包路径  如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setEntity("entity");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");//设置控制器包名
        pc.setMapper("mapper");
        pc.setDto("dto");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                Map<String, String> replaceMap = new HashMap<>();
                replaceMap.put("龘","\\$");
                map.put("replace", replaceMap);
                setMap(map);
            }
        };
        //自定义模板
       List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templatesCTSEC/dto.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return ConfigBuilder.joinPath(OUT_PUT_DIR, PARENT_PACKAGE + StringPool.DOT + "dto") + "/" + tableInfo.getEntityName() + "DTO.java";
            }
        });
        cfg.setFileOutConfigList(focList);

        mpg.setCfg(cfg);

        TemplateConfig tc = new TemplateConfig();
        tc.setController("/templatesCTSEC/controller.java.vm");
        tc.setService("/templatesCTSEC/service.java.vm");
        tc.setServiceImpl("/templatesCTSEC/serviceImpl.java.vm");
        tc.setEntity("/templatesCTSEC/entity.java.vm");
        tc.setMapper("/templatesCTSEC/mapper.java.vm");

        //tc.setXml("/templatesCTSEC/mapper.xml.vm");
        tc.setXml(null);
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg.setTemplate(tc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        //strategy.setSuperEntityClass("com.ctsec.dzsw.core.entity.BaseEntity");//自定义继承的Entity类全称，带包名
        strategy.setEntityLombokModel(true);//【实体】是否为lombok模型（默认 false）
        strategy.setRestControllerStyle(true);//生成 @RestController 控制器
        strategy.setSuperControllerClass("com.ctsec.dzsw.core.controller.BaseController");//自定义继承的Controller类全称，带包名
        strategy.setInclude(TABLES);//需要包含的表名，允许正则表达式
        //strategy.setSuperEntityColumns("ID");//自定义基础的Entity类，公共字段
        strategy.setControllerMappingHyphenStyle(false);//驼峰转连字符
        strategy.setTablePrefix(TABLE_PRE_FIX);//表前缀
        mpg.setStrategy(strategy);
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());


        mpg.execute();
    }


}