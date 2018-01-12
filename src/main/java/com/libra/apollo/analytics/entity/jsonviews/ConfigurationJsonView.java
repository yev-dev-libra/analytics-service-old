package com.libra.apollo.analytics.entity.jsonviews;

/**
 * Json Views used for the configurations controller. It is used by apolloAnalytics, views and investment styles
 * Apollo analytics has linked views and views has linked investment styles
 * 
 * Public returns only basic information like id, name, description ... and it does not involve getting deeper on the linked objects with a relation.
 * PublicExtended we obtain all the linked objects from the started object so if we call it from apollo analytics we obtain all the objects until investment styles.
 * 
 * Sample:
 * ConfigurationJsonView.Public on ApolloAnalytics returns:
 *     {
 *     	"id":1,
 *      "name":"...",
 *      "description":"..."
 *     }
 *     
 * ConfigurationJsonView.PublicExtended on ApolloAnalytics returns:
 *     {
 *      "id":1,
 *      "name":"...",
 *      "description":"...",
 *      "analyticsViews":[
 *          {
 *           "id":1,
 *           "name":"...",
 *           "description":"..."
 *           "investmentStyles":[
 *               {
 *                 "id":1,
 *                 "name":"...",
 *                 "description":"..."
 *               },
 *               {...}
 *            ]
 *           },
 *          {
 *           "id":2,
 *           "name":"...",
 *           "description":"...."
 *           "investmentStyles":[
 *               {
 *                 "id":2,
 *                 "name":"...",
 *                 "description":"..."
 *               },
 *               {...}
 *            ]
 *           }
 *       ]}      
 */
public class ConfigurationJsonView {
	public class Public {}
	public class PublicExtended extends Public {}
	public class Internal extends Public {}
}
