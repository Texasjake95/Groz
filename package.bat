@Echo off
del Groz.jar
mkdir classes
mkdir src
move "groz" "src"
javac -sourcepath src -classpath classes; src\groz\*.java -d classes
jar cfe Groz.jar groz.Groz -C classes .
rmdir classes /s /q
move "src/groz" "..\groz"
rmdir src /s /q
pause