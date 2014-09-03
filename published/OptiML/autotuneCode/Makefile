all:
	sed -i.mybak s/new\ \(resourceInfo.thread_id\)/new/ auto_kernelcode.cpp
	g++ -O0 -g auto_driver.cpp -o auto_driver.out

clean:
	rm -f *.out *.mybak
