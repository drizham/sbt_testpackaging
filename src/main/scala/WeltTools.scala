// WeltTools.scala
//package com.izham.welt
// file name does not have to be the same name as the package name
import java.text._
import java.util._

package com.izham.welt{
	class Tools{	// renamed from WeltTools
		// parse date string
		// takes in date string of this format: 2016-04-01 00:02:21+02:00
		// Returns ISO date of weltwunder time in (Zurich time zone)
		def dateToISO8601 (in: String) : String = {
		    val inDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ssX")
		    val inter0 = inDateFormat.parse(in) // intermediate date format
		    
		    // ISO8601 date format
		    val sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ") // can replace Z with z
		    val tzZurich = TimeZone.getTimeZone("Europe/Zurich")// set time zone- Welt Wunder Schweiz
		    sdf.setTimeZone(tzZurich)
		    sdf.format(inter0) // return ISO Date
		}

		// Welt der wunder case class
		case class LogWelt(channelKey: String, startDate: String, endDate: String,
		                  assetId: String, title: String)

		// Parses Welt Wunder log lines
		// Returns LogWelt class with 
		def parse(line: String) = {
		    val pieces = line.split(",")
		    // PlayListStartDate,ChannelKey,PlaylistEndDate,AssetID,Title
		    val channelKey = pieces(0)
		    val startDate = dateToISO8601(pieces(1))
		    val endDate = dateToISO8601(pieces(2))
		    val assetId = pieces(3)
		    val title = pieces(4)
		    LogWelt(channelKey, startDate, endDate, assetId, title)
		    }

		// Helper function to print LogWelt case class
		def retLogWelt(in: LogWelt): String = {
		    val array0 = Array(in.channelKey,in.startDate,in.endDate,in.assetId,in.title)
		    val strOut = array0.mkString(",")
		    strOut
		}

		// Helper function to print start date time and assetId and title
		def retInterest0(in: LogWelt): String = {
		    val array0 = Array(in.startDate, in.assetId, in.title)
		    val strOut = array0.mkString(",")
		    strOut
		}

		// Helper function to print LogWelt case class
		def printLogWelt(in: LogWelt): String = {
		    val array0 = Array(in.channelKey,in.startDate,in.endDate,in.assetId,in.title)
		    val strOut = array0.mkString(",")
		    strOut
		}

		// Deprecated function - TODO: Remove on production version 
		def getLinesOfInterest(in0: Array[String], line: String) = {
		    val in1 = in0.toSet // to remove repeating values
		    //TODO: At the moment this only prints to the console
		    in1.map(x => if(line.contains(x)) println(retInterest0(parse(line))) else ("not found"))
		}

		// returns line (raw welt log line) if there is a key match
		// else returns empty string ""
		// in0 Array of keys to match in line
		// line - raw string (not just a log line)
		def getLinesOfInterest2(in0: Array[String], line: String): String = {
		    val in1 = in0.toSet.toList // to remove repeating values
		    var ret0 = ""	// TODO: solution without a var
		    for (x <- in1 if line.contains(x)) ret0 = line
		    ret0
		}
	}

}


