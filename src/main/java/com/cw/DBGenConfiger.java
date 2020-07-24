/**
 * 
 */
package com.cw;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author  xueyongfei
 * @date 2020年7月20日
 */
@Setter
@Getter
public class DBGenConfiger {
	private boolean overwrite;
	private String targetProject;
	private String resourceProject;
	private String testProject;
	private String packageName;
	private String modelPackage;
	private String xmlPackage;
	private String mapperPackage;
	private String repoPackage;
	private String profile;
	private boolean needGenerateTest;
	private String[] tables;
	private String projectPath;

	private DBGenConfiger() {
	}

	private DBGenConfiger(Builder builder) {
		this.overwrite = builder.overwrite;
		this.targetProject = builder.targetProject;
		this.resourceProject = builder.resourceProject;
		this.testProject = builder.testProject;
		this.packageName = builder.packageName;
		this.modelPackage = builder.modelPackage;
		this.xmlPackage = builder.xmlPackage;
		this.mapperPackage = builder.mapperPackage;
		this.repoPackage = builder.repoPackage;
		this.profile = builder.profile;
		this.needGenerateTest = builder.needGenerateTest;
		this.tables = builder.tables;
		this.projectPath = builder.projectPath;
	}

	public static class Builder {
		private boolean overwrite = false;
		private String targetProject = "src/main/java";
		private String resourceProject = "src/main/resources";
		private String testProject = "src/test/java";
		private String packageName;
		private String modelPackage = "model";
		private String xmlPackage = "mapper";
		private String mapperPackage = "mapper";
		private String repoPackage = "repo";
		private String profile;
		private boolean needGenerateTest = false;
		private String[] tables;
		private String projectPath;

		public Builder overwrite(boolean overwrite) {
			this.overwrite = overwrite;
			return this;
		}

		public Builder targetProject(String targetProject) {
			this.targetProject = targetProject;
			return this;
		}

		public Builder resourceProject(String resourceProject) {
			this.resourceProject = resourceProject;
			return this;
		}

		public Builder testProject(String testProject) {
			this.testProject = testProject;
			return this;
		}

		public Builder packageName(String packageName) {
			this.packageName = packageName;
			return this;
		}

		public Builder modelPackage(String modelPackage) {
			this.modelPackage = modelPackage;
			return this;
		}

		public Builder xmlPackage(String xmlPackage) {
			this.xmlPackage = xmlPackage;
			return this;
		}

		public Builder mapperPackage(String mapperPackage) {
			this.mapperPackage = mapperPackage;
			return this;
		}

		public Builder repoPackage(String repoPackage) {
			this.repoPackage = repoPackage;
			return this;
		}

		public Builder profile(String profile) {
			this.profile = profile;
			return this;
		}

		public Builder needGenerateTest(boolean needGenerateTest) {
			this.needGenerateTest = needGenerateTest;
			return this;
		}

		public Builder tables(String... tables) {
			this.tables = tables;
			return this;
		}

		public Builder projectPath(String projectPath) {
			this.projectPath = projectPath;
			return this;
		}

		public DBGenConfiger build() {
			// 项目路径默认值
			if (StringUtils.isEmpty(this.projectPath)) {
				this.projectPath = StringUtils.substringBefore(this.getClass().getResource("/").getPath(), "target");
			}

			return new DBGenConfiger(this);
		}
	}
}
