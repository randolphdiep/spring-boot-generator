'use strict';
const Generator = require('yeoman-generator');
const chalk = require('chalk');
const _ = require('lodash');
const log = console.log;
const { execSync } = require('child_process');

module.exports = class extends Generator {

    constructor(args, opts) {
        super(args, opts);
        this.configOptions = this.config.getAll() || {};
    }

    // configuring() {
        
        // this.config.set(this.configOptions);
        // Object.assign(this.configOptions, constants);
    // }

    initializing() {
        // this.destinationRoot(path.join(this.destinationRoot(), '/MEO'));
        // this.logSuccess('Generating JPA entity, repository, service and controller');
        // return {
        //     validateEntityName() {
        //         const context = this.context;
        //         console.log(`EntityName: ${this.options['entityName']}, basePath: ${this.options['base-path']}`);
        //     }
        // }
    }

    logSuccess(msg) {
        log(chalk.bold.green(msg));
    }

    logWarn(msg) {
        log(chalk.keyword('orange')(msg));
    }

    logError(msg) {
        log(chalk.bold.red(msg));
    }

    // generateMainJavaCode(configOptions, templates) {
    //     const mainJavaRootDir = 'src/main/java/';
        // console.log(configOptions);
        // console.log("meome");
        // console.log(configOptions.packageFolder);
    //     this._generateCode(configOptions, templates, 'app/', mainJavaRootDir, configOptions.packageFolder);
    // }

    generateMainJavaCode(configOptions, templates) {
        const mainJavaRootDir = 'src/main/java/';
        // console.log(configOptions);
        this._generateCode(configOptions, templates, 'app/', mainJavaRootDir, this.configOptions.packageFolder);
    }

    generateMainResCode(configOptions, templates) {
        const mainResRootDir = 'src/main/resources/';
        this._generateCode(configOptions, templates, 'app/', mainResRootDir, '');
    }

    generateTestJavaCode(configOptions, templates) {
        const testJavaRootDir = 'src/test/java/';
        this._generateCode(configOptions, templates, 'app/', testJavaRootDir, configOptions.packageFolder);
    }

    generateFiles(configOptions, templates, srcRoot, baseFolder) {
        this._generateCode(configOptions, templates, srcRoot, baseFolder, '');
    }

    _generateCode(configOptions, templates, srcRoot, baseFolder, packageFolder) {
        templates.forEach(tmpl => {
            if (_.isString(tmpl)) {
                this.fs.copyTpl(
                    this.templatePath(srcRoot + baseFolder + tmpl),
                    this.destinationPath(baseFolder + packageFolder + '/' + tmpl),
                    configOptions
                );
            } else {
                this.fs.copyTpl(
                    this.templatePath(srcRoot + baseFolder + tmpl.src),
                    this.destinationPath(baseFolder + packageFolder + '/' + tmpl.dest),
                    { 
                        configOptions: configOptions,
                        packageName: this.configOptions.packageName
                    }
                );
            }
        });
    }

    _formatCode(configOptions) {
        if (configOptions.buildTool === 'maven') {
            this._formatCodeMaven();
        } else {
            this._formatCodeGradle();
        }
    }

    _formatCodeMaven() {
        const command = this._isWin() ? 'mvnw.bat' : './mvnw';
        execSync(`${command} spotless:apply`, {stdio: 'inherit'});
    }

    _formatCodeGradle() {
        const command = this._isWin() ? 'gradlew.bat' : './gradlew';
        execSync(`${command} googleJavaFormat`, {stdio: 'inherit'});
    }

    _isWin() {
        return process.platform === 'win32';
    }
};
