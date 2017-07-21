package EndPoints

import Common.Headers._
import io.gatling.core.Predef._
import io.gatling.http.Predef._


object POSTMappedBrandInfoBy
{

  private val bdsBrandPath : String = "/bds/api/v1/brands"
  private val bdsMappingPath : String = "/bds/api/v1/manage/mapping"
  private val baseUrl = "http://bdsqa.hushaboom.concurasp.com"

  /*
    Method: GET
    Description: List of brand by Mapping using Vendor Code
    Responses:
    [200] -  Successful response
     400  - Bad request all the required fields are not supplied including header
     404  - no contents found
     500  - error payload
 */
  def BrandName(vendorCode : String = "ZCT", queryName : String = "Concur Travel Gold", statusIs : Int = 200) = group("PostMappedBrandInfo") {
      exec(http("BrandName")
      .post(baseUrl + bdsMappingPath + "/" + vendorCode + "?QueryName=" + queryName)
      .headers(header_07)
      .body(ElFileBody("""C:\Gatling\performance\scripts\IntelliJ\BDSPerformance\src\test\resources\payload\no_region.json""")).asJSON
      .check(status.is(200)))
    //  need to handle redirection here.
  }

  /*
     Method: POST
     Description: List of brand are returned matching Vendor Code with no region
     Responses:
     [200] -  Successful response
      400  - Bad request all the required fields are not supplied including header
      404  - no contents found
      500  - error payload
  */
  def NoRegion() = group("PostMappedBrandInfo") {
    exec(http("NoRegion")
      .post(baseUrl + bdsMappingPath + "/bds/api/v1/brands/")
      .headers(header_07)
      .body(ElFileBody("""C:\Gatling\performance\scripts\IntelliJ\BDSPerformance\src\test\resources\payload\no_region.json""")).asJSON
      .check(status.is(200)))
    //  need to handle redirection here.
  }

  /*
      Method: POST
      Description: List of brand are returned matching Vendor Code with specific region
      Responses:
      [200] -  Successful response
       400  - Bad request all the required fields are not supplied including header
       404  - no contents found
       500  - error payload
   */
  def SpecificRegionExactMatch() = group("PostMappedBrandInfo") {
    exec(http("SpecificRegionExactMatch")
      .post(baseUrl + bdsMappingPath + "/bds/api/v1/brands/")
      .headers(header_07)
      .body(ElFileBody("""C:\Gatling\performance\scripts\IntelliJ\BDSPerformance\src\test\resources\payload\specific_region_match_found.json""")).asJSON
      .check(status.is(200)))
    //  need to handle redirection here.
  }

  /*
      Method: POST
      Description: List of brand are returned matching Vendor Code with reverse match
      Responses:
      [200] -  Successful response
       400  - Bad request all the required fields are not supplied including header
       404  - no contents found
       500  - error payload
   */
  def SpecificRegionReverseMatch() = group("PostMappedBrandInfo") {
    exec(http("SpecificRegionReverseMatch")
      .post(baseUrl + bdsMappingPath + "/bds/api/v1/brands/")
      .headers(header_07)
      .body(ElFileBody("""C:\Gatling\performance\scripts\IntelliJ\BDSPerformance\src\test\resources\payload\specific_region_expected_reverse.json""")).asJSON
      .check(status.is(200)))
    //  need to handle redirection here.
  }

  /*
        Method: POST
        Description: List of brand are returned wth region not found returned based on free definition
        Responses:
        [200] -  Successful response
         400  - Bad request all the required fields are not supplied including header
         404  - no contents found
         500  - error payload
     */
  def SpecificRegionNotFoundReturnedFreeDefinition() = group("PostMappedBrandInfo") {
    exec(http("SpecificRegionNotFoundReturnedFreeDefinition")
      .post(baseUrl + bdsMappingPath + "/bds/api/v1/brands/")
      .headers(header_07)
      .body(ElFileBody("""C:\Gatling\performance\scripts\IntelliJ\BDSPerformance\src\test\resources\payload\specific_region_match_not_found_free_definition.json""")).asJSON
      .check(status.is(200)))
    //  need to handle redirection here.
  }

  /*
        Method: POST
        Description: Specific brand returned wth region not found and region free
        Responses:
        [200] - Successful response
         400  - Bad request all the required fields are not supplied including header
         404  - no contents found
         500  - error payload
     */
  def SpecificRegionMatchNotFoundRegionFree() = group("PostMappedBrandInfo") {
    exec(http("SpecificRegionMatchNotFoundRegionFree")
      .post(bdsMappingPath + "/bds/api/v1/brands/")
      .headers(header_07)
      .body(ElFileBody("""C:\Gatling\performance\scripts\IntelliJ\BDSPerformance\src\test\resources\payload\specific_region_match_found.json""")).asJSON
      .check(status.is(200)))
    //  need to handle redirection here.
  }

  /*
        Method: POST
        Description: Specific brand returned wth region not found and free definition not found
        Responses:
        [200] -  Successful response
         400  - Bad request all the required fields are not supplied including header
         404  - no contents found
         500  - error payload
     */
  def SpecificRegionMatchNotFoundFreeDefinitionNotFound() = group("PostMappedBrandInfo") {
    exec(http("SpecificRegionMatchNotFoundFreeDefinitionNotFound(")
      .post(baseUrl + bdsMappingPath + "/bds/api/v1/brands/")
      .headers(header_07)
      .body(ElFileBody("""C:\Gatling\performance\scripts\IntelliJ\BDSPerformance\src\test\resources\payload\specific_region_match_found.json""")).asJSON
      .check(status.is(200)))
    //  need to handle redirection here.
  }
  
}
