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
		import scala.io.Source     // read string from file
		import java.io.PrintWriter // write strings to file

		try {
			val termFilePath = args(0)	// get term list file path
			val inFilePath = args(1)	// get the input file path
			val outFilePath = args(2)	// output file path

			println("Terms file path: " + termFilePath)
			println("Clean file written to: " + outFilePath)

			val source = Source.fromFile(inFilePath)

			val searchFor = io.Source.fromFile(termFilePath).getLines.toArray
			println("Terms searched for:")		
			searchFor.foreach(println)

			val lineIterator = source.getLines // get iterator
			val out = new PrintWriter(outFilePath) // file to write column data to
			
			out.println("PlayListStartDate, AssetID, Title")	// print header in output file
			
			val tool = new Tools	// intstantiate welt log tools	

			for (l <- lineIterator){
			    if (l.startsWith("Welt der Wunder Loop")) {// only parse data lines
			        if(tool.getLinesOfInterest2(searchFor, l) != "") out.println(tool.retInterest0(tool.parse(l)))
			    }
			}
			out.close()

			} catch {
				case ex: Exception => println("Error: Either search terms file/ log file not found")
			}
	}
}
