# Java SDK for Yumpu

[![Build Status](https://travis-ci.org/Yumpu/YumpuJavaSDK.svg?branch=master)](https://travis-ci.org/Yumpu/YumpuJavaSDK)

With the Yumpu java SDK you can use the Yumpu API in java. With this you can publish your pdf documents. Find out how to get started, publish your first pdf as flip book on the web or use the search function to display content.

## Introduction

What is Java SDK? To make it simple, SDK defines a set of functions wich developers can use to perform requests and receive data from the REST API.

Common characteristics of Yumpu REST API resources are as follows:

1. You access the resource by sending an HTTP request to the API server. The server replies with a response that contains either the data you requested, or the status indicator, or even both.
2. All resources are located at http://api.yumpu.com/2.0.
3. All resources may return different HTTP status codes (e.g., HTTP Status Code 200 for success response or HTTP Status Code 400 for the bad request).
4. You request a particular resource by adding a particular path to the base URL that specifies the resource.

## Get started

Download the SDK and start coding.

Code:
```
//initialize the yumpu class (find your token: https://www.yumpu.com/account/profile/api)
Yumpu y = new Yumpu("your access token");


// example to get all your documents:

// set parameters
String[] params = {"limit=10", "offset=0"};

//call the getDocuments() function
//print it to terminal
System.out.println(y.getDocuments(params));
```

## Impressum

[i]-magazine AG<br>
Web-based, self-service (SaaS) marketing & magazine software

E-Mail: info@i-magazine.de<br>
Tel: +49 (0)89 41 61 70 20

Moosackerstraße 17<br>
9444 Diepoldsau, SG – Schweiz<br>
Firmenbuch-Nr: CH-320.1.081.510<br>
Mwst. Nummer: CHE-113.772.377
