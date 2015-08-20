name := "streaming-shutdown"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
	"org.apache.spark" %% "spark-core" % "1.4.1" % "provided",
	"org.apache.spark" %% "spark-streaming" % "1.4.1" % "provided"
)

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
