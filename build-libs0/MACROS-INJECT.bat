reg add "HKCU\Software\Microsoft\Command Processor" /v Autorun /d "doskey /macrofile=\"G:\WarchoLife\WarchoPortable\CommonDevs\Eclipse\eclipse-jee-2020-06-R-win32-x86_64-workspace\underconstruction.viaproject\build\libs\macros.doskey\"" /f
reg query "HKCU\Software\Microsoft\Command Processor" /v Autorun

REM https://superuser.com/questions/1134368/create-permanent-doskey-in-windows-cmd
REM HKEY_CURRENT_USER\SOFTWARE\Microsoft\Command Processor