'use strict';

const BaseGenerator = require('../base-generator');
const fs = require('fs');
const path = require('path');


module.exports = class extends BaseGenerator {

    constructor(args, opts) {
        super(args, opts);
        // this.configOptions = this.options.configOptions || {};
        this.configOptions = JSON.parse(fs.readFileSync('dataBE.json', 'utf8'));
    }

    configuring() {
        this.destinationRoot(path.join(this.destinationRoot(), '/'+this.configOptions.appName));
        this.config.set(this.configOptions);
    }

    default() {
        this.composeWith(require.resolve('../base'), {
            configOptions: this.configOptions
        });

        this.composeWith(require.resolve('../controller'), {
            configOptions: this.configOptions
        });
    }

};
