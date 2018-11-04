# response-validation
Tool to validate the format of API based on the OAS 3 definition.

## Background
This tool is for allowing the users to test and verify the format of the response of APIs based on the OAS 3 definition.

## Current Limitations
- Supports only format validation

## Future Development
- Support data(content) validation (static)
- Support data(content) validation (basis user input)
- Support data(content) validation (basis user input + other requests in execution bundle)
- Support scenario based validation (basis user defined workflow)

## Inputs:
- OAS 3 Definition
  - API List (Optional): List of APIs and possible response codes in each of them. This is required for displaying the test coverage summary.
  - API Request Definition (Optional): Request payload for given APIs. Utility:
    - Interactive Mode: prompt user for the request parameters
    - Automated Mode: pre-validate the request payload
  - Response Model Definition: Defintion of the various models possible in the response
- Response Format: Format of response of APIs, for various response codes. [Input format](#input-format-for-response-format-definition)
In case the API List is available, the API execution details (format) may be excluded as the operation ID of the APIs in the input would be used to execute the request.
Format of response for  Format of all responses for given HTTP response code should be same.

## Modes
- Interactive
- Automated

## Input Format for Response Format Definition
