:Set/unset "assume unchanged" bit.

@ECHO OFF
setlocal EnableDelayedExpansion

:input
SET /p p=assume unchanged?(y/n):
IF %p%==y GOTO assume
IF %p%==Y GOTO assume
IF %p%==n GOTO no-assume
IF %p%==N GOTO no-assume
GOTO input

:assume
@FOR /f "eol=: delims=" %%a IN (aulist.txt) DO (
	git update-index --assume-unchanged "%%a"
	IF !ERRORLEVEL! == 0 (
		ECHO Set assume unchanged: %%a
	)
)
GOTO :end

:no-assume
@FOR /f "eol=: delims=" %%a IN (aulist.txt) DO (
	git update-index --no-assume-unchanged "%%a"
	IF !ERRORLEVEL! == 0 (
		ECHO Set NO assume unchanged: %%a
	)
)
GOTO :end

:end
PAUSE