sample
======

Code sample for Solutionreach

DESCRIPTION

This code sample comprises a simple Thesaurus service that can be accessed through an HTTP request.
The service allows you to add and remove words to your own personal archived thesaurus. 

USAGE

To add a word or words to your personal archive: Point your browser to http://localhost/thesaurus/add/{WORD_LIST} 
where {WORD_LIST} is one or more (comma delimited) English words you would like to add to your personal archived 
thesaurus. The word(s) will be found in a flat file thesaurus located on disk, and a list of synonyms will be 
displayed next to it along with the date and time the word was added to the archive. All words will be converted 
to their lower-case format. All thesaurus entries in your personal archive will be listed in alphabetical order.

To remove a word or words from your personal archive: Point your browser to http://localhost/thesaurus/remove/{WORD_LIST} 
where {WORD_LIST} is one or more (comma delimited) English words you would like to remove from your personal archived 
thesaurus if they exist.


RUNNING CODE SAMPLE

1. Clone the git repository: git clone https://github.com/miwest/sample.git

2. Go into local repository: cd sample

3. Untar the customized karaf distribution to run the sample in: tar -xzf apache-karaf-2.3.4.tgz

4. Build the sample and run the unit tests using maven and JDK 7: mvn install

5. Copy the newly built karaf archive into the deploy dir in karaf: cp assembly/target/assembly-1.0.0.kar apache-karaf-2.3.4/deploy/

6. Go into karaf to run the sample: cd apache-karaf-2.3.4/bin/

7. Start the karaf shell: ./karaf

8. Watch the log: log:tail (in the karaf shell)

9. Wait a couple minutes for everything to load and start up. 
   You should see the log output of "Listening on port 8080" when it is fully deployed and ready.

10. Point your browser to http://localhost:8080/thesaurus/add/happy,sad to add the words "happy" and "sad" to your archive

11. Point your browser to http://localhost:8080/thesaurus/remove/happy,sad to remove the words "happy" and "sad" from your archive 

12. Alternatively you may see the full request using cURL: curl -i -X GET http://localhost:8080/thesaurus/add/happy,sad

13. To exit the log: <CTRL> c

14. To exit the karaf shell: shutdown
