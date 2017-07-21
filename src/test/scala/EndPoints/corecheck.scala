package EndPoints

/**
  * Created by johno on 3/29/17.
  */

/* http://bdsdev.hushaboom.concurasp.com/bds/api/v1/brands/NZ/ */
// HTTP requests have to be passed to the exec() method in order to be attached to the scenario and be executed.
// GetVendorCode_NZ - request name - acts as a key when computing stats for the reports
// exec() - HTTP req have to be passed to exec() method in order to be attached to the sceanrio and be executed

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.sun.javafx.fxml.expression.Expression
import io.gatling.core.session.Expression
import Common.Headers._
import io.gatling.core.structure.ChainBuilder


object corecheck {


  private val flexFaringVendors = Array("2N", "3H", "5T", "6I", "7F", "8P", "9M", "9W", "AC", "AI", "AY", "BH", "D8",
    "DX", "DY", "JQ", "JV", "MO", "NZ", "P6", "PB", "PD", "QF", "S2", "SK", "TF",
    "TL", "UK", "VA", "WF", "WG", "WS", "YN", "ZL")


  private val numOfVendors : Integer = flexFaringVendors.length
  private val lowerIndex : Integer = 0
  private val randomNumber = new scala.util.Random
  private val randomIndex : Integer  = 0 + randomNumber.nextInt((numOfVendors - 0) + 1)
  private val endPointValue : String = flexFaringVendors(randomIndex - 1) + "/"
  private val bdsBrandPath : String = "/bds/api/v1/brands"
  private val bdsMappingPath : String = "/bds/api/v1/manage/mappings"
  private val payload : String = """[{ "vendorcode": "ZCT","brandname": "Concur Travel Silver","region": {"starti
ng_country": "","ending_country": ""}}]""""


  def GetVendorCode(vendorCode : String = "ZCT", statusIs : Int = 200) = exec(http("GetVendorCode: " + vendorCode)       //embedded style    http is the entry point
    .get("/bds/api/v1/vendors/" + vendorCode + "/")
    .headers(header_02)
    .check(status.is(statusIs)))

  /*
    Method: GET
    Description: Gets Exact brand info back or list of brands if vendor only is supplied
      Responses:
    [200] - Successful response with list of brands found and uproccessed brands list where match was not found
      400  - Bad request because all the required fields are not supplied including header
      404  - No contents found
    500  - error payload
      */
  def GetBrandInfo(vendorCode : String = "ZCT", statusIs : Int = 200): ChainBuilder = exec(http("GetBrandInfo: " + vendorCode)
    .get(bdsBrandPath + "/" + vendorCode +  "/")
    .headers(header_03)
    .check(status.is(statusIs)))

  /*
    Method: GET
    Description: List of brand by Mapping using BrandName
    Responses:
    [200] - Successful response with list of brand by mapping using query
     400  - Bad request all the required fields are not supplied including header
     404  - no contents found
     500  - error payload
 */
  def GetBrandInfoByUSRegionQuery(vendorCode : String, brandName : String, region : String, statusIs : Int = 200): ChainBuilder = exec(http("GetBrandInfoByRegionQuery: " + region)
    .get(bdsBrandPath + "/" + vendorCode + "/" + brandName + "?region=" + region)
    .headers(header_08)
    .check(status.is(200)))

  /*
    Method: GET
    Description: List of brand by query of region free definition
    Responses:
    [200] - Successful response with list of brand by mapping using query
     400  - Bad request all the required fields are not supplied including header
     404  - no contents found
     500  - error payload
 */
  def GetBrandInfoByRegionDefinitionQuery(vendorCode : String, brandName : String, region : String, statusIs : Int = 200): ChainBuilder = exec(http("GetBrandInfoByRegionDefinitionQuery: " + region)
    .get(bdsBrandPath + "/" + vendorCode + "/" + brandName + "?region=" + region)
    .headers(header_08)
    .check(status.is(statusIs)))

  /*
 Method: GET
 Description: Get exact band info back or list of brands if vendor only is supplied
 Responses:
 [200] - Successful response with list of brands found and unproccessed brands list where match was 'not' found
 400  - Bad request all the required fields are not supplied including header
 404  - no contents found
 500  - error payload
 */
  def GetBrandNameWithVendorCode(vendorCode : String, brandName : String, statusIs : Int = 200): ChainBuilder = exec(http("GetBrandNameWithVendorCode: " + vendorCode)
    .get(bdsBrandPath + "/" + vendorCode + "/" + brandName + "/")
    .headers(header_03)
    .check(status.is(statusIs)))

  /*
    Method: GET
    Description: List of brand by Mapping using Vendor Code
    Responses:
    [200] - Successful response with list of brand by mapping using vendor code
     400  - Bad request all the required fields are not supplied including header
     404  - no contents found
     500  - error payload
 */
  def GetMappedFullBrandNameByQueryName(brandName : String, queryName : String, statusIs : Int = 200): ChainBuilder = exec(http("GetMappedFullBrandNameByQueryName: " + queryName)
    .get(bdsMappingPath + "/" + brandName + "?QueryName=" + queryName)
    .headers(header_03)
    .check(status.is(statusIs)))

  /*
    Method: GET
    Description: List of brand by Mapping using Vendor Code
    Responses:
    [200] - Successful response with list of brand by mapping using query
     400  - Bad request all the required fields are not supplied including header
     404  - no contents found
     500  - error payload
 */
  def GetMappedBrandNameByVendorCode(vendorCode : String, statusIs : Int = 200): ChainBuilder = exec(http("GetMappedBrandNameByVendorCode: " + vendorCode)
    .get(bdsMappingPath + "/" + vendorCode + "/")
    .headers(header_08)
    .check(status.is(statusIs)))

  /*
      Method: GET
      Description: List of brand by Mapping using BrandName
      Responses:
      [200] - Successful response with list of brand by mapping using query
       400  - Bad request all the required fields are not supplied including header
       404  - no contents found
       500  - error payload
   */
  def GetMappedBrandNameByBrandName(brandName : String, mappedBrandName : String, statusIs : Int = 200): ChainBuilder = exec(http("GetMappedBrandNameByBrandName: " + brandName)
    .get( bdsMappingPath + "/" + brandName + "?name=" + mappedBrandName)
    .headers(header_08)
    .check(status.is(statusIs)))

  /*
     Method: POST
     Description: List of brand are returned matching Vendor Code and brand name
     Responses:
     [200] - Successful response with list of brands found and unproccessed brands list where match was 'not' found
      400  - Bad request all the required fields are not supplied including header
      404  - no contents found
      500  - error payload
  */

  def POSTBrands() = exec(http("POSTBrandsNZ")
    .post("/bds/api/v1/brands/")
    .headers(header_07)
    .body(StringBody("""[{ "vendorcode": "ZCT","brandname": "Concur Travel Silver","region": {"starting_country": "","ending_country": ""}}]""""))
    .check(status.is(200)));

  def PostBrandInfoByNoRegion() = exec(http("PostBrandInfoByNoRegion")
    .post("/bds/api/v1/brands/")
    .headers(header_07)
    .body(StringBody("\"\"" + "\"" + payload + "\"\"" + "\""))
    .check(status.is(200)));

  def PostBrandInfoBySpecificRegionExactMatch(): ChainBuilder = exec(http("PostBrandInfoBySpecificRegionExactMatch")
    .post("/bds/api/v1/brands/")
    .headers(header_07)
    .body(StringBody("""[{"vendorcode": "ZCT","brandname": "Concur Travel Gold","region":{ "starting_country": "CA", "ending_country": "USAA" }}]""""))
    .check(status.is(200)))

  def PostBrandInfoBySpecificRegionReverseMatch(): ChainBuilder = exec(http("PostBrandInfoBySpecificRegionReverseMatch")
    .post("/bds/api/v1/brands/")
    .headers(header_07)
    .body(StringBody("""[{"vendorcode": "ZCT","brandname": "Concur Travel Gold","region":{ "starting_country": "CA", "ending_country": "US" }}]""""))
    .check(status.is(200)))

  def PostBrandInfoBySpecificRegionNotFoundReturnedFreeDefinition(): ChainBuilder = exec(http("PostBrandInfoBySpecificRegionNotFoundReturnedFreeDefinition")
    .post("/bds/api/v1/brands/")
    .headers(header_07)
    .body(StringBody("""[{"vendorcode": "ZCT","brandname": "Concur Travel Gold","region":{ "starting_country": "CA", "ending_country": "USAA" }}]""""))
    .check(status.is(200)))

  def PostBrandInfoBySpecificRegionMatchNotFoundRegionFree(): ChainBuilder = exec(http("PostBrandInfoBySpecificRegionMatchNotFoundRegionFree")
    .post("/bds/api/v1/brands/")
    .headers(header_07)
 //   .body(ElFileBody("""C:\Gatling\performance\scripts\IntelliJ\BDSPerformance\src\test\resources\payload\specific_region_match_found.json""")).asJSON
    .body(StringBody("""[{"vendorcode": "ZCT","brandname": "Concur Travel Gold","region":{ "starting_country": "US", "ending_country": "CA" }}]""""))
    .check(status.is(200)))

  def PostBrandInfoBySpecificRegionMatchNotFoundFreeDefinitionNotFound(): ChainBuilder = exec(http("PostBrandInfoBySpecificRegionMatchNotFoundFreeDefinitionNotFound")
    .post("/bds/api/v1/brands/")
    .headers(header_07)
    .body(StringBody("""[{"vendorcode": "ZCT","brandname": "Concur Travel Gold","region":{ "starting_country": "CA", "ending_country": "USAA" }}]""""))
    .check(status.is(200)))
}


