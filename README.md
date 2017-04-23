# JRis
JRis is a Java implementation of RIS Format.
JRis has the goal to give an easy way to parse RIS format stream and to build them.

From [Wikipiedia](https://en.wikipedia.org/wiki/RIS_(file_format)):
> RIS is a standardized tag format developed by Research Information Systems, Incorporated (the format name refers to the company) to  enable citation programs to exchange data. It is supported by a number of reference managers. Many digital libraries, like IEEE Xplore, Scopus, the ACM Portal, Scopemed, ScienceDirect, SpringerLink and Rayyan QCRI can export citations in this format. Major reference/citation manager applications, like Zotero, Mendeley, and EndNote can export and import citations in this format.

## Usage example
The following examples inlustrates how to use JRis to parse and to build RIS format stream.
* To parse a RIS stream you could use one of the `Jris.parse()` methods. Each one accepts as argument a different type of Reader, and will return a `List<Record>` of records. Each record is a single RIS element parsed from the stream.
* To build a RIS stream you could use one of the `Jris.build()` methods. Each one accepts as arguments a `List<Record>` from which the RIS stream will be generated and a different type of Writer in which the stream will be write.
