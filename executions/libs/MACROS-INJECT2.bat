reg add "HKCU\Software\Microsoft\Command Processor" /v Autorun /d "doskey wya=wia -dir /%cd%/% $*" /f
reg query "HKCU\Software\Microsoft\Command Processor" /v Autorun

REM https://superuser.com/questions/1134368/create-permanent-doskey-in-windows-cmd
REM HKEY_CURRENT_USER\SOFTWARE\Microsoft\Command Processor