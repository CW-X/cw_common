/**
 * 
 */
package com.cw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 *
 * @author  xueyongfei
 * @date 2020年7月20日
 */
public class CodeGenerator {
//	public static void main(String[] args) {
//
//		// AlgCodeGenerator.setConfig(new
//		// AlgDbGenConfigurer.Builder().packageName("com.ccbft.sarabids").modelPackage("dstrubut").tables("Gds_Rgst","Gds_Dstrubut","Gds_Dstrubut_Jrnl").build());
//		CodeGenerator.setConfig(new DBGenConfiger.Builder().packageName("com").modelPackage("cw")
//				.tables("user", "stand").build());
//		CodeGenerator.generate();
//	}

	private static DBGenConfiger myBatisCodeGenConfigurer;

	public static void setConfig(DBGenConfiger myBatisCodeGenConfigurer) {
		CodeGenerator.myBatisCodeGenConfigurer = myBatisCodeGenConfigurer;
	}

	public static void generate() {
		if (myBatisCodeGenConfigurer == null) {
			myBatisCodeGenConfigurer = new DBGenConfiger.Builder().build();
		}
		final SpringApplication springApplication = new SpringApplication(CodeGenerator.class);

		springApplication.setWebApplicationType(WebApplicationType.NONE);
		springApplication.run();
	}

	@Bean
	public ApplicationRunner runner(Environment env) {
		return args -> {
			generator(env);
		};
	}

	private void generator(Environment env) throws IOException {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 1. 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = myBatisCodeGenConfigurer.getProjectPath();
		gc.setOutputDir(projectPath + "/" + myBatisCodeGenConfigurer.getTargetProject()); // 输出目录
		gc.setAuthor(System.getProperty("user.name")); // 开发人员
		gc.setFileOverride(myBatisCodeGenConfigurer.isOverwrite()); // 是否覆盖已有文件
//            gc.setOpen(true);//打开输出目录
		gc.setSwagger2(true);// 开启 swagger2 模式
		gc.setIdType(IdType.INPUT);// 指定生成的主键的ID类型
		mpg.setGlobalConfig(gc);

		// 2. 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();

		dsc.setUrl(env.getProperty("spring.datasource.url"));
		dsc.setDriverName(env.getProperty("spring.datasource.driver-class-name"));
		dsc.setUsername(env.getProperty("spring.datasource.username"));
		dsc.setPassword(env.getProperty("spring.datasource.password"));
		mpg.setDataSource(dsc);

		// 3. 包配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName(myBatisCodeGenConfigurer.getModelPackage());
		pc.setParent(myBatisCodeGenConfigurer.getPackageName());
		mpg.setPackageInfo(pc);

		// 4. 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {

			}
		};

		// 如果模板引擎是 freemarker
		String templatePath = "/templates/mapper.xml.ftl";

		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return projectPath + myBatisCodeGenConfigurer.getResourceProject() + "/mapper/" + pc.getModuleName()
						+ "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});

		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		// 5. 配置模板
		TemplateConfig templateConfig = new TemplateConfig();

		templateConfig.setXml(null);
		mpg.setTemplate(templateConfig);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());

		// 6. 数据库生成策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);// 数据库表映射到实体的命名策略
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);// 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
		strategy.setEntityLombokModel(true);// 是否为lombok模型
		strategy.setRestControllerStyle(true);// 生成 @RestController 控制器
		strategy.setInclude(myBatisCodeGenConfigurer.getTables());// 需要包含的表名
//            strategy.setSuperEntityColumns("id");
		strategy.setControllerMappingHyphenStyle(true);// 驼峰转连字符
		strategy.setTablePrefix(pc.getModuleName() + "_");// 表前缀
//            strategy.setEntityTableFieldAnnotationEnable(true);//是否生成实体时，生成字段注解
		mpg.setStrategy(strategy);
		mpg.execute();
	}
}
