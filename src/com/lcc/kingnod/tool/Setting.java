package com.lcc.kingnod.tool;

/**
 * Created by asus on 2017/3/21.
 */
public class Setting {

    /**
     * 操作类型
     */
    public enum ActionType {
        create("创建"), update("修改"), view("视图");
        private final String typeName;

        ActionType(final String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }
}
