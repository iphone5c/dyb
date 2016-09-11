Ext.define('DYB.utils.DetailLayout', {

    /* Begin Definitions */

    alias: ['layout.dyb-ex-detaillayout'],
    extend: 'Ext.layout.container.Table',

    /* End Definitions */

    type: 'dyb-ex-detaillayout',

    // ====入口参数定义===================================================================
    /**
     * 入口参数
     */
    config: {
        rowHeight: 30,
        columnWidths: [],
        labelBgColor: '#EFF7FC',
        cellPadding: 5,
        lineColor: '#CECECE'
    },

    // ====构造方法========================================================================
    constructor: function (config) {
        var me = this;
        config = config || {};
        Ext.applyIf(config, me.config);
        me.callParent(arguments);
        me.columns = me.columnWidths.length;
    },


    getRenderTree: function () {
        var me = this,
            items = me.getLayoutItems(),
            cells,
            rows = [],
            result = Ext.apply({
                tag: 'table',
                role: 'presentation',
                cls: me.tableCls,
                cellspacing: 0,
                cellpadding: 0,
                cn: {
                    tag: 'tbody',
                    role: 'presentation',
                    cn: rows
                }
            }, me.tableAttrs),
            tdAttrs = me.tdAttrs,
            needsDivWrap = me.needsDivWrap(),
            i, len = items.length, item, curCell, tr, rowIdx, cellIdx, cell;

        //扩展 应用表样式 caven
        me.applyTableLocalStyle(result);

        // Calculate the correct cell structure for the current items
        cells = me.calculateCells(items);

        for (i = 0; i < len; i++) {
            item = items[i];

            curCell = cells[i];
            rowIdx = curCell.rowIdx;
            cellIdx = curCell.cellIdx;

            // If no row present, create and insert one
            tr = rows[rowIdx];
            if (!tr) {
                tr = rows[rowIdx] = {
                    tag: 'tr',
                    role: 'presentation',
                    cn: []
                };
                if (me.trAttrs) {
                    Ext.apply(tr, me.trAttrs);
                }
            }

            // If no cell present, create and insert one
            cell = tr.cn[cellIdx] = {
                tag: 'td',
                role: 'presentation'
            };
            if (tdAttrs) {
                Ext.apply(cell, tdAttrs);
            }
            Ext.apply(cell, {
                colSpan: item.colspan || 1,
                rowSpan: item.rowspan || 1,
                id: item.cellId || '',
                cls: me.cellCls + ' ' + (item.cellCls || '')
            });

            //扩展 应用单元格样式 caven
            me.applyCellLocalStyle(cell, rowIdx, cellIdx, item);

            if (needsDivWrap) { //create wrapper div if needed - see docs below
                cell = cell.cn = {
                    tag: 'div',
                    role: 'presentation'
                };
            }

            me.configureItem(item);
            // The DomHelper config of the item is the cell's sole child
            cell.cn = item.getRenderTree();
        }
        return result;

    },
    applyCellLocalStyle: function (cell, rowIdx, cellIdx, item) {
        var me = this, width = 0, height = 0;
        if (me.columnWidths[cellIdx] > 0)
            width += me.columnWidths[cellIdx];
        else
            width = 0;
        for (var i = cellIdx + 1; i < cellIdx + cell.colSpan; i++) {
            if (me.columnWidths[i] > 0)
                width += me.columnWidths[i];
            else
                width = 0;
        }
        height = cell.rowSpan * me.rowHeight;
        if (!cell.style) {
            cell.style = 'overflow: hidden;';
            if (me.cellPadding)
                cell.style += 'padding:' + me.cellPadding + 'px;';
            if (me.lineColor && me.lineColor != '')
                cell.style += 'border-bottom:1px solid ' + me.lineColor + ';border-right:1px solid ' + me.lineColor + ';';
            if (width > 0)
                cell.style += 'width:' + width + 'px;';
            if (height > 0)
                cell.style += 'height:' + height + 'px;';
            if (item.islabel === true && this.labelBgColor)
                cell.style += 'background-color: ' + this.labelBgColor + ';';
            if (item.wrap !== true)
                cell.style += 'white-space:nowrap;';
        }
        else {
            Ext.apply(table.style, {overflow: 'hidden'});
            if (me.cellPadding)
                Ext.apply(table.style, {padding: me.cellPadding});
            if (me.lineColor && me.lineColor != '')
                Ext.apply(table.style, {borderBottom: '1px solid ' + me.lineColor, borderRight: '1px solid ' + me.lineColor});
            if (width > 0)
                Ext.apply(table.style, {width: width});
            if (height > 0)
                Ext.apply(table.style, {height: height});
            if (item.islabel === true && this.labelBgColor)
                Ext.apply(table.style, {backgroundColor: this.labelBgColor});
            if (item.wrap !== true)
                Ext.apply(table.style, {whiteSpace: 'nowrap'});
        }
    },
    applyTableLocalStyle: function (table) {
        var me = this;
        if (!table.style) {
            table.style = 'table-layout:fixed;';
            if (me.lineColor && me.lineColor != '')
                table.style += 'border-top:1px solid ' + me.lineColor + ';border-left:1px solid ' + me.lineColor + ';';
        }
        else {
            Ext.apply(table.style, {tableLayout: 'fixed'});
            if (me.lineColor && me.lineColor != '')
                Ext.apply(table.style, {borderTop: '1px solid ' + me.lineColor, borderLeft: '1px solid ' + me.lineColor });
        }

    }

});