package com.axelor.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Scanner;


import javax.ws.rs.ext.Provider;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



@Provider
public class SecurityFilter{

	public static final String  AUTHORIZATION_HEADER_KEY = "fc08e6e2cc529af6657bc70967f33cf0-us20";
						String url = "https://us20.api.mailchimp.com/3.0/lists/cd7b27032c/";
						
						String name = "Anything";
						String password = AUTHORIZATION_HEADER_KEY;
						String authString = name + ":" + password;
						
						byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
						String authStringEnc = new String(authEncBytes);
						
						JSONObject obj = new JSONObject();
						JSONArray jArray = new JSONArray();
						JSONParser parse = new JSONParser(); 
						
						URL urlConnector; 
						
						public void setup() {
							try {
								url = "https://us20.api.mailchimp.com/3.0/lists/cd7b27032c/members";
						        urlConnector = new URL(url);
						        System.err.println(url);
						    } catch (MalformedURLException ex) {
						        throw new RuntimeException(ex);
						    }
							
						}
						
						HttpURLConnection httpURLConnection; 
						
						public void connection() {
							try {
								 urlConnector = new URL(url);
							        System.err.println(url);
								httpURLConnection = (HttpURLConnection) urlConnector.openConnection();
								httpURLConnection.setRequestMethod("GET");
								httpURLConnection.setDoOutput(true);
								httpURLConnection.setDoInput(true);
								httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
								httpURLConnection.setRequestProperty("Accept", "application/json");
								httpURLConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
								InputStream is1 = httpURLConnection.getInputStream();
								
		
								
								StringBuilder sb = new StringBuilder();
								BufferedReader br = new BufferedReader(new InputStreamReader(is1, "utf-8"));
								String line = null;
								while ((line = br.readLine()) != null) {
								    sb.append(line + "\n");
								    System.out.println(line);
							
								}
								
								
								br.close();
								} catch (Exception ex) {
								  throw new RuntimeException();
								  
							  }
								httpURLConnection.disconnect();
						}
						
						
						public void getData() {
							
							try {
								url = "https://us20.api.mailchimp.com/3.0/lists/cd7b27032c/members";
								
								urlConnector = new URL(url);
						        System.err.println(url);
							
								httpURLConnection = (HttpURLConnection) urlConnector.openConnection();
								httpURLConnection.setRequestMethod("GET");
								httpURLConnection.setDoOutput(true);
								httpURLConnection.setDoInput(true);
								httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
								httpURLConnection.setRequestProperty("Accept", "application/json");
								httpURLConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
								int responseCode = httpURLConnection.getResponseCode();
								System.err.println(responseCode);
								InputStream is2 = httpURLConnection.getInputStream();		
					
								StringBuilder sb = new StringBuilder();
								BufferedReader br = new BufferedReader(new InputStreamReader(is2, "utf-8"));
//								String myString = IOUtils.toString(is2,"UTF-8");
								String line = null;
						
								while ((line = br.readLine()) != null) {
								    sb.append(line + "\n");
								    System.out.println(line);
								    
								}
//								
//								JSONObject obj1 = (JSONObject) parse.parse(myString);
//				
//								JSONArray jsonarr_1 = (JSONArray) obj1.get("members");
//								
//								for(int i =0; i<jsonarr_1.size();i++) {
//									
//									JSONObject obj2 = (JSONObject)jsonarr_1.get(i);
//
//									JSONArray jsonarr_2 = (JSONArray) obj2.get("merge_fields");
//									
//									if(obj2.get("email_address").equals("p1@gmail.com")) {
//									
//									System.out.println("Elements under results array");
//									System.out.println("\n id:" + obj2.get("id"));
//									System.out.println("\n Email Address :" + obj2.get("email_address"));
//									}
//									
//									for(int j=0; j< jsonarr_2.size();j++) {
//									
//										JSONObject obj3 = (JSONObject) jsonarr_2.get(j);
//										
//										String fName = (String) obj3.get("FNAME");
//										System.out.println("\n First Name: " + fName);
//									}
//								}
//						
//								
								br.close();
								} catch (Exception ex) {
									System.err.println(ex);
								  throw new RuntimeException();
								  
							  }
							httpURLConnection.disconnect();
							
						}
						
						
						public	void postData() {
							try {
								url = "https://us20.api.mailchimp.com/3.0/lists/cd7b27032c/members";
							
								urlConnector = new URL(url);
						        System.err.println(url);
							
								HttpURLConnection httpURLConnection2 = (HttpURLConnection) urlConnector.openConnection();
								httpURLConnection2.setRequestMethod("POST");
								httpURLConnection2.setDoOutput(true);
								httpURLConnection2.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
								httpURLConnection2.setRequestProperty("Authorization", "Basic " + authStringEnc);
							
								
								String test ="{\"email_address\" : \"per2@gmail.com\",\"status\" : \"subscribed\",\"merge_fields\" : {\"FNAME\" : \"Priyansh\",\"LNAME\"	: \"Sharma\" } }";
								byte[] testing = test.getBytes();
								
//								Map<String,Object> params = new LinkedHashMap<String,Object>();
//								params.put("email_address", "test@gmail.com");
//								params.put("status", "subscribed");
//								
//								StringBuilder stringBuilder = new StringBuilder();
//								
//								for(Map.Entry<String, Object> param :params.entrySet()){
//									if(stringBuilder.length() !=0) stringBuilder.append("&");
//									stringBuilder.append(param.getKey());
//									stringBuilder.append("=");
//									stringBuilder.append(param.getValue());
//									
//								}
					// 		    byte[] postDataBytes = stringBuilder.toString().getBytes("UTF-8");
								httpURLConnection2.getOutputStream().write(testing);
								
								Reader in = new BufferedReader(new InputStreamReader(httpURLConnection2.getInputStream(), "UTF-8"));
								StringBuilder sb = new StringBuilder();
								for(int c; (c = in.read()) >= 0;) 
									sb.append((char)c);
									String response = sb.toString();
									
									System.err.println(response);	
									
								
							}catch(Exception ex){
								System.err.println(ex);
							}
						}
					
						
					 public	void delete(String id) {
							try {
								url = "https://us20.api.mailchimp.com/3.0/lists/cd7b27032c/members/";
								url = url + id ;
								urlConnector = new URL(url);
															
								httpURLConnection = (HttpURLConnection) urlConnector.openConnection();
								httpURLConnection.setRequestMethod("DELETE");
								httpURLConnection.setDoOutput(true);
								httpURLConnection.setDoInput(true);
								httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
								httpURLConnection.setRequestProperty("Accept", "application/json");
								httpURLConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
								int responseCode = httpURLConnection.getResponseCode();
								System.err.println(responseCode);		
					
								} catch (Exception ex) {
									System.err.println(ex);
								  throw new RuntimeException();
								  
							  }
							httpURLConnection.disconnect();
						
						}
					
					 
					 public	String find(String email) {
							
						try{
							url = "https://us20.api.mailchimp.com/3.0/lists/cd7b27032c/members";
							urlConnector = new URL(url);
							
							String test = null;
							httpURLConnection = (HttpURLConnection) urlConnector.openConnection();
							httpURLConnection.setRequestMethod("GET");
							httpURLConnection.setDoOutput(true);
							httpURLConnection.setDoInput(true);
							httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
							httpURLConnection.setRequestProperty("Accept", "application/json");
							httpURLConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
							int responseCode = httpURLConnection.getResponseCode();
							System.err.println(responseCode);
							InputStream is2 = httpURLConnection.getInputStream();		
				
						
							BufferedReader br = new BufferedReader(new InputStreamReader(is2, "utf-8"));
							String myString = IOUtils.toString(is2,"UTF-8");

							JSONObject obj1 = (JSONObject) parse.parse(myString);
			
							JSONArray jsonarr_1 = (JSONArray) obj1.get("members");
							
							for(int i =0; i<jsonarr_1.size();i++) {
								
								JSONObject obj2 = (JSONObject)jsonarr_1.get(i);

								if(obj2.get("email_address").equals(email)) {
								
								System.out.println("Elements under results array");
								System.out.println("\n id:" + obj2.get("id"));
								 test = obj2.get("id").toString();
								System.out.println(test);
								System.out.println("\n Email Address :" + obj2.get("email_address"));
								}
								
								
							}

							br.close();
							httpURLConnection.disconnect();
							System.out.println(test);
							return test;
							
							} catch (Exception ex) {
								System.err.println(ex);
							  throw new RuntimeException();
							  
						 }
							
							
					}
					
					
					public void update(String id) {
						
						try {
							url = "https://us20.api.mailchimp.com/3.0/lists/cd7b27032c/members/";
							url = url  + id;
							urlConnector = new URL(url);

							httpURLConnection = (HttpURLConnection) urlConnector.openConnection();
							httpURLConnection.setRequestMethod("PUT");
							httpURLConnection.setDoOutput(true);
							httpURLConnection.setDoInput(true);
							httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
							httpURLConnection.setRequestProperty("Accept", "application/json");
							httpURLConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
							
							String test ="{\"email_address\" : \"pagser@gmail.com\",\"status\" : \"subscribed\",\"merge_fields\" : {\"FNAME\" : \"PaN\",\"LNAME\"	: \"CAR\" } }";
							
							
							
							JSONObject jsonObject = (JSONObject) parse.parse(test);
									byte[] testing = jsonObject.toString().getBytes();
							httpURLConnection.getOutputStream().write(testing);
							
							Reader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
							//System.err.println(new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream())).readLine().toString());
							
							
							StringBuilder sb = new StringBuilder();
							for(int c; (c = in.read()) >= 0;) 
								sb.append((char)c);
								String response = sb.toString();
								
								System.err.println(response);	
							
						}catch(Exception ex){
							
							System.err.println(ex);
						}
						
					}
					
					
					public void addFields() {
						
						try{
							url = "https://us20.api.mailchimp.com/3.0/lists/cd7b27032c/";
							url = url + "merge-fields/";

							urlConnector = new URL(url);
												
							
							httpURLConnection = (HttpURLConnection) urlConnector.openConnection();
							httpURLConnection.setRequestMethod("POST");
							httpURLConnection.setDoOutput(true);
							httpURLConnection.setDoInput(true);
							httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
							httpURLConnection.setRequestProperty("Accept", "application/json");
							httpURLConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
								
				
							String fields ="{\n" + 
									"	\"name\" : \"Caste\",\n" + 
									"	\"type\"  : \"text\"\n" + 
									"}";
							
							
							JSONObject jsonObj = (JSONObject) parse.parse(fields);
							
							
							
							byte[] insertField = jsonObj.toString().getBytes();
								
							httpURLConnection.getOutputStream().write(insertField);

					//		System.err.println(new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream())).readLine().toString());
						
							Reader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
							StringBuilder sb = new StringBuilder();
							for(int c; (c = in.read()) >= 0;) 
								sb.append((char)c);
								String response = sb.toString();
								
								System.err.println(response);	
								
							
						}catch(Exception ex){
							System.err.println(ex);
						}
							
						
						
					}
					
						public static void main(String[] args) {
							
							SecurityFilter securityFilter = new SecurityFilter();
//							securityFilter.setup();
							securityFilter.connection();
							securityFilter.getData();
//							securityFilter.postData();
							Scanner sc = new Scanner(System.in);
							System.out.println("Enter the email you want to delete");
							String email = sc.nextLine();
							String id = securityFilter.find(email);
							securityFilter.delete(id);
							
							System.out.println("Enter Email to update");
							String email2 = sc.nextLine();
							
							String updateId = securityFilter.find(email2);
							securityFilter.update(updateId);
							securityFilter.addFields();
							
						}

}
