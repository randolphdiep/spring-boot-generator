'use strict';
const BaseGenerator = require('../base-generator');
const constants = require('../constants');
const _ = require('lodash');


module.exports = class extends BaseGenerator {

    constructor(args, opts) {
        super(args, opts);
        this.configOptions = this.options.configOptions || {};
    }

    configuring() {
        // this.configOptions = Object.assign({}, this.configOptions, this.config.getAll());
        // this.configOptions.basePath = '/' + this.options['base-path'];
        // this.configOptions.entityName = this.options.entityName;
        this._configEntityVar(this.configOptions);
        // this.configOptions.entityVarName = _.camelCase(this.configOptions.entityName);
        // this.configOptions.tableName = _.lowerCase(this.options.entityName)+'s';
        // this.configOptions.supportDatabaseSequences =
        //     this.configOptions.databaseType === 'h2'
        //     || this.configOptions.databaseType === 'postgresql';
    }

    writing() {
        // console.log(this.configOptions.entities[0].fields);
        this._generateModelConfig(this.configOptions);
        this._generateResConfig(this.configOptions);
        // this._generateAppCode(this.configOptions);
    }

    end() {

    }

    _configEntityVar(configOptions){
        configOptions.entities.forEach(entity =>{
            entity.entityVarName = _.lowerCase(entity.entityName);
            entity.fields.forEach(field =>{
                field.fieldVarName = this._capitalizeFirstLetter(field.fieldName);
                field.fieldMapName = _.snakeCase(field.fieldName);
            });
        }); 
    }

    _capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }

    _generateModelConfig(configOptions) {
        configOptions.entities.forEach(entity =>{
            
            var mainJavaTemplates = [
                {src: 'entities/Entity.java', dest: 'entities/'+entity.entityName+'.java'},
                {src: 'repositories/Repository.java', dest: 'repositories/'+entity.entityName+'Repository.java'},
                {src: 'dto/Request.java', dest: 'dto/'+entity.entityName+'Request.java'},
                {src: 'dto/Response.java', dest: 'dto/'+entity.entityName+'Response.java'},
                {src: 'services/Service.java', dest: 'services/'+entity.entityName+'Service.java'},
                {src: 'web/controllers/Controller.java', dest: 'controllers/'+entity.entityName+'Controller.java'},
            ];
            this.generateMainJavaCode(entity, mainJavaTemplates);
        });
    }
    _generateResConfig(configOptions) {
        configOptions.entities.forEach(entity =>{
            
            var mainJavaTemplates = [
                {src: 'query/SQL.xml', dest: 'query/'+entity.entityName+'SQL.xml'},
            ];
            this.generateMainResCode(entity, mainJavaTemplates);
        });
    }
    
    // _generateAppCode(configOptions) {
    //     const mainJavaTemplates = [
    //         {src: 'entities/Entity.java', dest: 'entities/'+configOptions.entityName+'.java'},
    //         {src: 'repositories/Repository.java', dest: 'repositories/'+configOptions.entityName+'Repository.java'},
    //         {src: 'dto/Request.java', dest: 'dto/'+configOptions.entityName+'Request.java'},
    //         {src: 'dto/Response.java', dest: 'dto/'+configOptions.entityName+'Response.java'},
            // {src: 'services/Service.java', dest: 'services/'+configOptions.entityName+'Service.java'},
            // {src: 'web/controllers/Controller.java', dest: 'controllers/'+configOptions.entityName+'Controller.java'},
        // ];
        // this.generateMainJavaCode(configOptions, mainJavaTemplates);

        // const testJavaTemplates = [
        //     {src: 'web/controllers/ControllerTest.java', dest: 'controllers/'+configOptions.entityName+'ControllerTest.java'},
        //     {src: 'web/controllers/ControllerIT.java', dest: 'controllers/'+configOptions.entityName+'ControllerIT.java'},
        // ];
        // this.generateTestJavaCode(configOptions, testJavaTemplates);
    // }
};
