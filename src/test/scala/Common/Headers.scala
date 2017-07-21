package Common

/**
  * Created by johno on 9/26/16.
  */

import io.gatling.core.Predef._
import io.gatling.http.Predef._

// the common configuration to all HTTP request
object Headers {

  val httpProtocol = http
    .baseURL("http://bdsqa.hushaboom.concurasp.com")  // the base URL
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png"""), WhiteList())
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")   // common http header that will be sent with all the requests
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:48.0) Gecko/20100101 Firefox/48.0")


  val header_01 = Map(
    "Accept" -> "application/json",
    "concur-correlationid" -> "7db61d20-4460-4f09-95a3-661a8dca228e"
  )

  val header_02 = Map(
    "Accept-Language" -> "en-US",
    "Content-Type" -> "application/json",
    "Concur-Correlationid" -> "7db61d20-4460-4f09-95a3-661a8dca228e",
    "Appid" -> "Mobile",
    "userid" -> "yshmaelid"
  )

  val header_03 = Map(
    "userid" -> "yshid",
    "appid" -> "1233"
  )

  val header_04 = Map(
    "Concur-Correlationid" -> "7db61d20-4460-4f09-95a3-661a8dca228e",
    "Appid" -> "Mobile",
    "VendorCode" -> "NZ"
  )

  val header_05 = Map(
    "Concur-Correlationid" -> "7db61d20-4460-4f09-95a3-661a8dca228e",
    "Appid" -> "Mobile",
    "VendorCode" -> "NZ",
    "Brandname" -> "Business Class Non-Refundable"
  )

  val header_06 = Map(
    "Concur-Correlationid" -> "7db61d20-4460-4f09-95a3-661a8dca228e",
    "Appid" -> "Mobile",
    "userid" -> "yshid",
    "VendorCode" -> "NZ"
  )

  val header_07 = Map(
    "Accept-Language" -> "en-US",
    "Concur-Correlationid" -> "6f5a7634-a0d7-474e-8aef-acde6c80421d",
    "Appid" -> "Mobile",
    "userid" -> "yshid",
    "Content-Type" -> "application/json"
  )

  val header_08 = Map(
    "Accept-Language" -> "en-US",
    "Appid" -> "1234",
    "UserId" -> "yshmael",
    "pcc" -> "header",
    "acc" -> "en-US",
    "concur-correlationid" -> "yshmael",
    "VendorCode" -> "ZCT"
  )
}