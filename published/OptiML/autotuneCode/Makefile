all:
	sed -i.mybak s/new\ \(resourceInfo.thread_id\)/new/ kernelInit.cpp
	g++ -O0 -g -fpermissive driver.cpp -o driver.out

clean:
	rm -f *.out *.mybak
