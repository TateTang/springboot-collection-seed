package com.company.project;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.*;

/**
 * @Author tangmf
 * @Date 2020/4/21 13:48
 * @Description 代码生成器，快速生成对应的 Entity、Mapper、Mapper XML、Service、Controller简化开发。
 */
@Slf4j
public class CodeGenerator extends AutoGenerator {
	private static final String BASE_PACKAGE_NAME = "com.company.project";// 生成代码所在的基础包名称
	private static final String MODULE_PACKAGE_NAME = BASE_PACKAGE_NAME + ".modules";
	// JDBC配置，请修改为你项目的实际配置
	private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT";
	private static final String JDBC_USERNAME = "root";
	private static final String JDBC_PASSWORD = "root";
	private static final String JDBC_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

	private static final String PROJECT_PATH = System.getProperty("user.dir");// 项目在硬盘上的基础路径
	private static final String JAVA_PATH = PROJECT_PATH + "/src/main/java"; // java文件路径
	private static final String AUTHOR = "tangmf";// 作者

	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	private static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(("Please input " + tip + ": "));
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotBlank(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("Error input " + tip + "!");
	}

	/**
	 * 继承自AutoGenerator，复写了execute方法，将controller的path设置为null，可以避免自动生成controller类。
	 */
	@Override
	public void execute() {
		log.debug("==========================准备生成文件...==========================");
		PackageConfig packageInfo = this.getPackageInfo();
		DataSourceConfig dataSource = this.getDataSource();
		StrategyConfig strategy = this.getStrategy();
		TemplateConfig template = this.getTemplate();
		GlobalConfig globalConfig = this.getGlobalConfig();
		AbstractTemplateEngine templateEngine = this.getTemplateEngine();

		// 初始化配置
		if (null == config) {
			config = new ConfigBuilder(packageInfo, dataSource, strategy, template, globalConfig);

			List<TableInfo> tableInfoList = config.getTableInfoList();
			if (tableInfoList.size() <= 0) {
				System.out.println("Table(s) " + Arrays.toString(new Set[]{strategy.getInclude()}) + " not exist!");
				return;
			}
			// 设置所有的entity都展示@TableName注解，展示是哪张数据库表
			for (TableInfo tableInfo : tableInfoList) {
				tableInfo.setConvert(true);
				boolean isDone = false;
				do {
					System.out.println("[INFO] Entity: " + tableInfo.getEntityName() + ",  Mapper: "
							+ tableInfo.getMapperName() + ", Service: " + tableInfo.getServiceName() + ", Controller: "
							+ tableInfo.getControllerName());
					String yes = scanner("Generate codes with options above? (Y/N)");
					if ("Y".equals(yes) || "y".equals(yes)) {
						isDone = true;
					} else {
						String entity = scanner("Entity ");
						String mapper = entity + "Mapper";
						String service = "I" + entity + "Service";
						String serviceImpl = entity + "ServiceImpl";
						String controller = entity + "Controller";
						tableInfo.setEntityName(entity);
						tableInfo.setMapperName(mapper);
						tableInfo.setServiceName(service);
						tableInfo.setServiceImplName(serviceImpl);
						tableInfo.setControllerName(controller);
					}
				} while (!isDone);
			}
			// 设置 CONTROLLER_PATH 为null，不生成 controller的时候需要使用
			// config.getPathInfo().put(ConstVal.CONTROLLER_PATH, null);
			config.getPathInfo().forEach((key, value) -> {
				if (value != null) {
					File dir = new File(value);
					if (!dir.exists()) {
						boolean result = dir.mkdirs();
						if (result) {
							log.debug("创建目录： [" + value + "]");
						}
					}
				}
			});
			// 初始化配置
			if (null != injectionConfig) {
				injectionConfig.setConfig(config);
			}
		}

		if (null == templateEngine) {
			// 为了兼容之前逻辑，采用 Velocity 引擎 【 默认 】
			templateEngine = new VelocityTemplateEngine();
		}
		// 模板引擎初始化执行文件输出
		templateEngine.init(this.pretreatmentConfigBuilder(config)).mkdirs().batchOutput().open();
		log.debug("==========================文件生成完成！！！==========================");
	}

	private static void generate(String moduleName, String tableName) {
		String tablePrefix = tableName.substring(0,
				tableName.indexOf('_') == -1 ? tableName.length() : tableName.indexOf('_'));// 表名带有下划线，这里可以参考mp官网进行修改

		// 代码生成器
		CodeGenerator generator = new CodeGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(JAVA_PATH);
		gc.setAuthor(AUTHOR);
		gc.setOpen(false);
		gc.setEnableCache(false);
		gc.setBaseResultMap(true);
		generator.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(JDBC_URL);
		dsc.setDriverName(JDBC_DRIVER_CLASS_NAME);
		dsc.setUsername(JDBC_USERNAME);
		dsc.setPassword(JDBC_PASSWORD);
		generator.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName(moduleName);
		pc.setParent(MODULE_PACKAGE_NAME);
		generator.setPackageInfo(pc);

		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setXml(null);
		generator.setTemplate(templateConfig);

		// 自定义配置，配置mapper.xml文件
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};
		// 如果模板引擎是 freemarker
		String templatePath = "/templates/mapper.xml.ftl";
		// 如果模板引擎是 velocity
		// String templatePath = "/templates/mapper.xml.vm";
		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return PROJECT_PATH + "/src/main/resources/mapper/" + pc.getModuleName() + "/"
						+ tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});
		cfg.setFileOutConfigList(focList);
		generator.setCfg(cfg);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setTablePrefix(tablePrefix);
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		strategy.setEnableSqlFilter(false);

		// 写于父类中的公共字段
//		strategy.setSuperEntityColumns("id");
		strategy.setInclude(tableName.split(","));
		strategy.setControllerMappingHyphenStyle(true);
		generator.setStrategy(strategy);
		generator.setTemplateEngine(new FreemarkerTemplateEngine());
		generator.execute();
	}

	private static void generateFromInput() {
		String moduleName = scanner("Module name");
		String tableName = scanner("Table name");
		generate(moduleName, tableName);
	}

	public static void main(String[] args) {
		generateFromInput();
	}
}
