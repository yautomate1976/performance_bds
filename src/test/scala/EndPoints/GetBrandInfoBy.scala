package EndPoints

import Common.Headers._
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import scala.util.Random


object GetBrandInfoBy
{

  private val bdsBrandPath : String = "/bds/api/v1/brands"
  private val bdsMappingPath : String = "/bds/api/v1/manage/mapping"
  private val baseUrl = "http://bdsqa.hushaboom.concurasp.com"
  private val feeder = csv("data/vendorcode.csv").random

/*
      Method: GET
      Description: List of brand by Vendor Code
      Responses:
      [200] -  Successful response
       400  - Bad request all the required fields are not supplied including header
       404  - no contents found
       500  - error payload
   */
  def VendorCode(vendorCode : String = "NZ", statusIs : Int = 200) = group("GetBrandInfo") {
    exec(http("VendorCode")
      .get(bdsBrandPath + "/" + vendorCode + "/")
      .headers(header_02)
      .check(status.is(statusIs)))
    //  need to handle redirection here.
  }

/*
    Method: GET
    Description: List of brand by Brand Name
    BrandName
    Responses:
    [200] -  Successful response
     400  - Bad request all the required fields are not supplied including header
     404  - no contents found
     500  - error payload
 */
  def BrandName(vendorCode : String = "NZ", brandName : String = "Concur Travel Silver", statusIs : Int = 200) = group("GetBrandInfoBy") {
    exec(http("BrandName")
      .get(bdsBrandPath + "/" + vendorCode + "/" + brandName + "/")
      .headers(header_02)
      .check(status.is(statusIs)))
    //  need to handle redirection here.
  }

/*
    Method: GET
    Description: List of brand by US BCA Region Query
    Responses:
    [200] -  Successful response
     400  - Bad request all the required fields are not supplied including header
     404  - no contents found
     500  - error payload
 */
  def USBCARegionDefinitionQuery(vendorCode : String = "NZ", brandName : String = "Concur Travel Silver",
                                 region: String = "US%3BCA", statusIs : Int = 200) = group("GetBrandInfoBy") {
    exec(http("USBCARegionDefinitionQuery")
      .get(bdsBrandPath + "/" + vendorCode + "/" + brandName + "?region=" + region )
      .headers(header_08)
      .check(status.is(statusIs)))
    //  need to handle redirection here.
  }

/*
     Method: GET
     Description: List of brand by CA BUS Region Query
     Responses:
     [200] -  Successful response
      400  - Bad request all the required fields are not supplied including header
      404  - no contents found
      500  - error payload
  */
  def CABUSRegionDefinitionQuery(vendorCode : String = "NZ", brandName : String = "Concur Travel Silver",
                                 region: String = "CA%3BUS", statusIs : Int = 200) = group("GetBrandInfoBy") {
    exec(http("CABUSRegionDefinitionQuery")
      .get(bdsBrandPath + "/" + vendorCode + "/" + brandName + "?region=" + region )
      .headers(header_08)
      .check(status.is(statusIs)))
    //  need to handle redirection here.
  }

/*
     Method: GET
     Description: List of brand by query of region free definition
     Responses:
     [200] -  Successful response
      400  - Bad request all the required fields are not supplied including header
      404  - no contents found
      500  - error payload
  */
  def CARegionDefinitionQuery(vendorCode : String = "NZ", brandName : String = "Concur Travel Silver",
                              region: String = "CA%3BUS", statusIs : Int = 200) = group("GetBrandInfoBy") {
    exec(http("CABUSRegionDefinitionQuery")
      .get(bdsBrandPath + "/" + vendorCode + "/" + brandName + "?region=" + region )
      .headers(header_08)
      .check(status.is(statusIs)))
    //  need to handle redirection here.
  }

/*
  Method: GET
  Description: Get exact band info back or list of brands if vendor only is supplied
  Responses:
  [200] -  Successful response
  400  - Bad request all the required fields are not supplied including header
  404  - no contents found
  500  - error payload
*/
  def BusinessClassNonRefundable(vendorCode : String = "NZ", brandName : String = "Business Class Non-Refundable", statusIs : Int = 200) = group("GetBrandInfoBy") {
    exec(http("BusinessClassNonRefundable")
      .get(bdsBrandPath + "/" + vendorCode + "/" + brandName)
      .headers(header_08)
      .check(status.is(statusIs)))
    //  need to handle redirection here.
  }

}
