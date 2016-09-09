/**
 * Created by Caven on 2015/1/20.
 */
Ext.define('DYB.utils.ExUtils', {
    requires: [ ],
    ex: function () {
        var me = this;
        Ext['exUtils'] = {
            combine:me.combine,
            combineIf:me.combineIf,
            refreshView:me.refreshView,
            uuid:me.uuid
        };
    },

    /**
     * 生成UUID
     * @param lowerCase {boolean} true全小写，false全大写, 默认false
     * @param hyphens {boolean} true生成“-”号，false不生成，默认false
     * @param braces {boolean} true生成"{}"号, false不生成, 默认false
     * @returns {string}
     */
    uuid: function (lowerCase, hyphens, braces) {
        var s = [];
        var hexDigits = "0123456789abcdef";
        for (var i = 0; i < 36; i++)
            s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
        s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
        s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
        s[8] = s[13] = s[18] = s[23] = (hyphens ? "-" : "");
        var uuid = s.join("");
        uuid = lowerCase ? uuid : uuid.toUpperCase();
        if (braces)
            uuid = '{' + uuid + '}';
        return uuid;
    },
    /**
     * 深度合并对象
     * @param object
     * @param config
     * @returns {*}
     */
    combine: function (object, config) {
        if (object && config && typeof config === 'object') {
            var i;
            for (i in config) {
                if (config[i] === null || config[i] === 'undefined')
                    continue;
                if (typeof config[i] !== 'object') {
                    object[i] = config[i];
                }
                else {
                    if (typeof object[i] === 'undefined')
                        object[i] = {};
                    Ext.combine(object[i], config[i]);
                }
            }
        }
        return object;
    },
    /**
     * 深度合并对象
     * @param object
     * @param config
     * @returns {*}
     */
    combineIf: function (object, config) {
        if (object && config && typeof config === 'object') {
            for (var i in config) {
                if (typeof config[i] !== 'object') {
                    if (typeof object[i] === 'undefined')
                        object[i] = config[i];
                }
                else {
                    if (typeof object[i] === 'undefined')
                        object[i] = config[i];
                    else
                        Ext.combineIf(object[i], config[i]);
                }
            }
        }
        return object;
    },
    /**
     * 刷新视图
     * @param view 视图
     */
    refreshView: function (view) {
        var newView = view;
        if (typeof view.ownerCt !== 'undefined'
            && typeof view.getConfigurator === 'function'
            && typeof view.getItemId === 'function') {
            var owner = view.ownerCt,
                className = Ext.getClassName(view),
                configNames = Ext.Object.getKeys(view.getConfigurator().configs),
                config = {},
                i = 0,
                index = -1;
            for (i = 0; i < configNames.length; i++)
                config[configNames[i]] = view.getConfig(configNames[i]);
            for (i = 0; i < owner.items.getCount(); i++) {
                if (owner.items.getAt(i).getItemId() == view.getItemId()) {
                    index = i;
                    break;
                }
            }
            if (index > -1) {
                newView = Ext.create(className, config);

                if (typeof view.meOwner !== 'undefined')
                    newView.meOwner = view.meOwner;
                if (typeof owner.innerView !== 'undefined' && owner.innerView == view)
                    owner.innerView = newView;

                if (typeof owner.setActiveTab === 'function')
                    owner.setActiveTab(newView);
                owner.insert(index, newView);
                owner.remove(view);
                owner.doLayout();
            }
        }
        return newView;
    }
})
;