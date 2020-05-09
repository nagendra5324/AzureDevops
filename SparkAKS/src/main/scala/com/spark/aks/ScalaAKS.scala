package com.spark.aks

import org.apache.spark._
import org.apache.spark.sql.SparkSession

object ScalaAKS {

  def main(args: Array[String]) {

    val spark: SparkSession = SparkSession.builder()
      .master("yarn")
      .appName("SparkByExamples.com")
      .getOrCreate()

    import spark.implicits._

    spark.conf.set("fs.azure.account.key.sparktesttoload.blob.core.windows.net", "MLy/5xUn5FtaZYkP6WvjKx1sZYj/dIeJnkuQlyLGZIovAjwbF7SOAxZjTCa9zEYYwB9SnnJa7tGQSRsDh4zdKg==")
    val path = "wasbs://testingcsv@sparktesttoload.blob.core.windows.net"
    val dataframe = spark.read.option("header", "true").option("inferSchema", "true").csv(path + "/TechCrunchcontinentalUSA.csv")
    dataframe.write.parquet("wasbs://testingcsv@sparktesttoload.blob.core.windows.net/Output_Parquet")

  }

}