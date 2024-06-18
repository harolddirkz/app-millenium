package com.devs.demoCours.Configuration;

import io.github.cdimascio.dotenv.Dotenv;


public class DotenvConfig {
    public static  void loadEnv(){
        Dotenv dotenv = Dotenv.configure().load();
        String MysqlUsername = dotenv.get("MYSQL_USERNAME");
        String MysqlPassword = dotenv.get("MYSQL_PASSWORD");
        String awsAccessKey = dotenv.get("AWS_ACCESS_KEY");
        String awsSecretKey = dotenv.get("AWS_SECRET_KEY");
        String awsS3BucketName = dotenv.get("AWS_S3_BUCKET_NAME");
        String awsRegion = dotenv.get("AWS_REGION");
        String MysqlUrl = dotenv.get("MYSQL_URL");

        assert MysqlUsername != null;
        System.setProperty("MYSQL_USERNAME",MysqlUsername);
        assert MysqlPassword != null;
        System.setProperty("MYSQL_PASSWORD",MysqlPassword);
        assert MysqlUrl != null;
        System.setProperty("MYSQL_URL",MysqlUrl);
        assert awsAccessKey != null;
        System.setProperty("AWS_ACCESS_KEY",awsAccessKey);
        assert awsSecretKey != null;
        System.setProperty("AWS_SECRET_KEY",awsSecretKey);
        assert awsS3BucketName != null;
        System.setProperty("AWS_S3_BUCKET_NAME",awsS3BucketName);
        assert awsRegion != null;
        System.setProperty("AWS_REGION",awsRegion);


    }

}
