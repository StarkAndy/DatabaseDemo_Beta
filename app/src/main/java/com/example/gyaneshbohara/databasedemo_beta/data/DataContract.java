package com.example.gyaneshbohara.databasedemo_beta.data;

import android.provider.BaseColumns;

public class DataContract {

    private DataContract(){}


    public static class DataEntry implements BaseColumns{


        public static final String TABLE_NAME = "downloadInfo";
        public static final String COLUMN_CUSTOM_ID="customid";
        public static final String COLUMN_LAYOUT_ID="layoutid";
        public static final String COLUMN_DATE="datastored";
        public static final String COLUMN_FILE_NAME = "filename";
        public static final String COLUMN_TOTAL_SIZE = "totalsize";
        public static final String COLUMN_REMAINING = "remaining";
        public static final String COLUMN_FLAG = "flag";



    }
}


