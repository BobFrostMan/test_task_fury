set hub_url=http://localhost:4444
set port=5556
set browser=chrome,maxinstance=1,platform=WINDOWS

call java -Dwebdriver.chrome.driver="../driver/bin/chromedriver.exe" -jar selenium-server-standalone-3.11.0.jar -role node -hub %hub_url%/grid/register -port %port% -browser browserName=%browser% -servlet org.openqa.grid.web.servlet.LifecycleServlet
