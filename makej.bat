@echo off
:help 
echo findj -- find Java directory for compiling on drive C:\
echo clean -- Delete all *.class and *.jar files
echo build -- Build Java project with all data and manifest in the directory
echo       -- the projectname should be given as parameter to the makej.bat
echo       -- without file extension
echo       -- standard is javaApp 
echo run   -- run the program
echo text  -- stream output to textfile out.txt
echo all   -- clean...build...run
echo quit  -- exit        
echo help  -- help     
if "%1" == "" GOTO param1n
GOTO param1j

:param1n
set JARNAME=JavaApp
echo Projectname is %JARNAME%
goto menu
:param1j
set JARNAME=%1
echo Projectname is %JARNAME%
goto menu

:menu
set /P input=Befehl:
if "%input%"=="findj" GOTO findj
if "%input%"=="clean" GOTO clean
if "%input%"=="build" GOTO build
if "%input%"=="run" GOTO run
if "%input%"=="text" GOTO text 
if "%input%"=="all" GOTO all 
if "%input%"=="quit" GOTO end
if "%input%"=="help" GOTO help
GOTO menu

:all 
del /Q .\bin\*.class
del /Q *.jar
rem del /Q out.txt
javac -g .\src\*.java -d .\bin
jar cvfm %JARNAME%.jar Manifest.mf -C bin/ .
java -jar %JARNAME%.jar
goto menu

:findj
echo Please wait a moment 
echo searching java development kit....
if exist tmp.txt GOTO found
dir C:\ /B /S /A:D | findstr C:\\Program.*\\Java\\jdk[0-9\._]*$ > tmp.txt
:found
echo following paths for jdk have been found:
for /F "tokens=*" %%i in ('type tmp.txt') do (
    echo %%i
)
goto menu


:clean
del /Q ".\bin\*.class"
del /Q *.jar
rem del /Q out.txt
goto menu
:build
javac -g .\src\*.java -d .\bin
jar cvfm %JARNAME%.jar Manifest.mf -C bin/ .
goto menu
:run
java -jar %JARNAME%.jar
goto menu
:text
java -jar %JARNAME%.jar > out.txt
goto menu

:end
set input=
set z=
set JARNAME=


