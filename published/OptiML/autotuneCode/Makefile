
all: clean
	sed -i.mybak s/new\ \(resourceInfo.thread_id\)/new/ auto_kernelcode.cpp 
	sed -i.mybak s/new\ \(resourceInfo.thread_id\)/new/ auto_init.cpp 
	g++ -O3 auto_driver.cpp -o auto_driver.cpp.0.out

clean:
	rm -f *.out *.mybak
        
