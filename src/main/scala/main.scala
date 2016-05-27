// Program to read in a text file with a list of search terms to
// look for within the raw log files
// Takes in three arguments
// arg0 - path to text file with terms to look for, each term must be on a new line
// arg1 - path to raw welt der wunder log file (*.rtf, *.txt, *.csv all works)
// arg2 - path to write report to 
object PrintReport{
//object main extends Any {

	def main(args: Array[String]) = {
		if (args.length !=3) {
			Console.err.println("Usage: PrintReport <terms list file> <input log> <output report>")
			Console.err.println("Terms list: file of terms to look for within log file")
			Console.err.println("Input log: raw log file")
			Console.err.println("Output report: file to write report to")
			System.exit(1)
		}

		import com.izham.welt.Tools
		
		val termFilePath = args(0)	// get term list file name
		println("Terms list file: " + termFilePath)

		val inFilePath = args(1)	// get the input file name
		println("Cleaning Welt der Wunder log: " + inFilePath)

		// TODO uncommment when file to write to works
		val outFilePath = args(2)
		println("Clean file written to: " + outFilePath)

		import scala.io.Source     // read string from file
		import java.io.PrintWriter // write strings to file

		val source = Source.fromFile(inFilePath)
		// TODO: Fix when out is required
		val out = new PrintWriter(outFilePath) // file to write column data to
		val lineIterator = source.getLines // get iterator
		
		// read in terms of interest
		// read terms to array (as the list is bound to be small)
		val searchFor = io.Source.fromFile(termFilePath).getLines.toArray
		println("Terms searched for:")		
		searchFor.foreach(println)

		// print header in output file
		out.println("PlayListStartDate, AssetID, Title")

		// tools
		val tool = new Tools		

		for (l <- lineIterator){
		    // skip the lines that contain "This report was.."
		    // only parse data lines
		    if (l.startsWith("Welt der Wunder Loop")) {
		        //getLinesOfInterest(searchFor, l)
		        if(tool.getLinesOfInterest2(searchFor, l) != "") out.println(tool.retInterest0(tool.parse(l)))
		        // out.println(getLinesOfInterest(searchFor, l))
		    }
		}
		// TODO: Uncommment when req
		out.close()		// close write file

	}
}
