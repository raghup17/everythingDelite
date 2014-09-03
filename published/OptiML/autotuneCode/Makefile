
all:
	sed -i.mybak s/new\ \(resourceInfo.thread_id\)/new/ auto_kernelcode.cpp 
	g++ -O3 auto_driver.cpp -o auto_driver.cpp.out

clean:
	rm -f *.out *.mybak

