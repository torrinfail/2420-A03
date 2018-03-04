#include<cstdlib>
#include<string>
#include<iostream>
using namespace std;
int main(int argc, char **argv)
{
	if(argc < 2)
	{
		cout << "Error: You Need To Provide The Name Of The Class To Build And Run (Without Any File Extention)";
		return -1;
	}

	string* buildCommand = new string("javac ");
       	buildCommand->append(argv[1]);
	buildCommand->append(".java");
 	
	string* runCommand = new string("java ");
	runCommand->append(argv[1]);
	
	system(buildCommand->c_str());
	system(runCommand->c_str());
	
	delete buildCommand;
	delete runCommand;
	return 0;	
}
