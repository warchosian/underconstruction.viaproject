echo off
set exedir=%cd%
set viadir=%~dp0
echo exe from: %exedir%
echo via from: %viadir%

echo === via -propfile %viadir%\via.properties -dir %exedir% %*
java -jar %viadir%\underconstruction.viaproject.jar -propfile %viadir%\via.properties -dir %exedir% %* 
