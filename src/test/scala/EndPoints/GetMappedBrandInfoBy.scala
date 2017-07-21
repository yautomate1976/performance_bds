package EndPoints

import Common.Headers._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import Common._

// private val flexFaringVendors = Array("2N", "3H", "5T", "6I", "7F", "8P", "9M", "9W", "AC", "AI", "AY", "BH", "D8",
//"DX", "DY", "JQ", "JV", "MO", "NZ", "P6", "PB", "PD", "QF", "S2", "SK", "TF",
// "TL", "UK", "VA", "WF", "WG", "WS", "YN", "ZL")


object GetMappedBrandInfoBy
{

  /*
    Method: GET
    Description: List of brand by Mapping using Vendor Code
    Responses:
    [200] -  Successful response
     400  - Bad request all the required fields are not supplied including header
     404  - no contents found
     500  - error payload
 */
  def FullBrandNameByQueryName(brandName : String = "ZCT" , queryName : String = "Concur Travel Gold", statusIs : Int = 200 ) = group("GetMappedBrandInfo") {
    exec(http("FullBrandNameByQueryName")
      .get(GlobalVar.bdsMappingPath + "/" + brandName + "?QueryName=" + queryName)
      .headers(header_08)
      .check(status.is(statusIs)))
    //  need to handle redirection here.
  }

  /*
    Method: GET
    Description: List of brand by Mapping using Vendor Code
    Responses:
    [200] -  Successful response
     400  - Bad request all the required fields are not supplied including header
     404  - no contents found
     500  - error payload
 */
  def BrandNameByVendorCode(brandName : String = "ZCT" , statusIs : Int = 200) = group("GetMappedBrandInfo") {
    exec(http("BrandNameByVendorCode")
      .get(GlobalVar.bdsMappingPath + "/" + brandName + "/")
      .headers(header_08)
      .check(status.is(statusIs)))
    //  need to handle redirection here.
  }

  /*
      Method: GET
      Description: List of brand by Mapping using BrandName
      Responses:
      [200] -  Successful response
       400  - Bad request all the required fields are not supplied including header
       404  - no contents found
       500  - error payload
   */
  def BrandNameByBrandName(brandName : String = "ZCT", queryName : String = "CONCURTRAVELGOLD1",
                           statusIs : Int = 200 ) = group("GetMappedBrandInfo") {
    exec(http("BrandNameByBrandName")
      .get(GlobalVar.bdsMappingPath + "/" + brandName + "?name=" + queryName)
      .headers(header_08)
      .check(status.is(statusIs)))
    //  need to handle redirection here.
  }

}
