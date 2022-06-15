package com.qa.api.gorest.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.tesults.tesults.*;

public class TesultsListener implements ITestListener {

	List<Map<String, Object>> testCases = new ArrayList<Map<String, Object>>();

	public static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("I am in onTestStart method " + getTestMethodName(result) + " start");

	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		Map<String, Object> testCase = new HashMap<String, Object>();
		testCase.put("name", getTestMethodName(iTestResult));
		testCase.put("suite", "TesultsExample");
		testCase.put("result", "pass");
		testCases.add(testCase);
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		Map<String, Object> testCase = new HashMap<String, Object>();
		testCase.put("name", getTestMethodName(iTestResult));
		testCase.put("suite", "TesultsExample");
		testCase.put("result", "fail");
		testCases.add(testCase);
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		Map<String, Object> testCase = new HashMap<String, Object>();
		testCase.put("name", getTestMethodName(iTestResult));
		testCase.put("suite", "TesultsExample");
		testCase.put("result", "fail");
		testCases.add(testCase);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		// Map<String, Object> to hold your test results data.
		Map<String, Object> data = new HashMap<String, Object>();
//		data.put("target", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6ImY3MDYxMGM4LWZkNzgtNGQyOS1iMmMxLTliZTcwNDc3ZTAyMC0xNTgyNjQ3MDg3MzE1IiwiZXhwIjo0MTAyNDQ0ODAwMDAwLCJ2ZXIiOiIwIiwic2VzIjoiMGVhMzI3YTAtYjFjMy00MGM4LWIyYzEtZThhOTFiZWI5YmJmIiwidHlwZSI6InQifQ.FAjYzVHN5pkkZztpqg7UYQZYtvqI2bHZtYexyl0hXjs");
		data.put("target", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6ImVkNTY4ZTExLTM2NzItNDBhNi1hYWMxLTQyNzJkZGZkODA4ZC0xNTkxODkwNTgxOTg4IiwiZXhwIjo0MTAyNDQ0ODAwMDAwLCJ2ZXIiOiIwIiwic2VzIjoiODA3NGMxYTUtM2M4Yi00MzM5LWE5MGUtNDQ3NGE5ODU1ZGIwIiwidHlwZSI6InQifQ.9TKuDyhX3P2OlZ70PwGD_e9Ti5iDh-JufHTruscnqwM");
		Map<String, Object> results = new HashMap<String, Object>();
		results.put("cases", testCases);
		data.put("results", results);

		// Upload
		Map<String, Object> response = Results.upload(data);
		System.out.println("success: " + response.get("success"));
		System.out.println("message: " + response.get("message"));
		System.out.println("warnings: " + ((List<String>) response.get("warnings")).size());
		System.out.println("errors: " + ((List<String>) response.get("errors")).size());
	}

}
