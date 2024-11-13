def fifo_page_replacement(frames, reference_string):
    memory = []
    page_faults = 0
    
    for page in reference_string:
        if page not in memory:
            if len(memory) < frames:
                memory.append(page)
            else:
                memory.pop(0)
                memory.append(page)
            page_faults += 1
        print(f"Page: {page} -> Memory: {memory}")
    
    print(f"Total page faults (FIFO): {page_faults}")

def optimal_page_replacement(frames, reference_string):
    memory = []
    page_faults = 0
    
    for i in range(len(reference_string)):
        page = reference_string[i]
        if page not in memory:
            if len(memory) < frames:
                memory.append(page)
            else:
                future = reference_string[i + 1:]
                indices = []
                for mem_page in memory:
                    if mem_page in future:
                        indices.append(future.index(mem_page))
                    else:
                        indices.append(float('inf')) 
                memory.pop(indices.index(max(indices)))
                memory.append(page)
            page_faults += 1
        print(f"Page: {page} -> Memory: {memory}")
    
    print(f"Total page faults (Optimal): {page_faults}")

def lru_page_replacement(frames, reference_string):
    memory = []
    page_faults = 0
    lru = []  
    
    for page in reference_string:
        if page not in memory:
            if len(memory) < frames:
                memory.append(page)
            else:
                least_recent = lru.pop(0)
                memory[memory.index(least_recent)] = page
            page_faults += 1
        else:
            lru.remove(page)
        
        lru.append(page)
        print(f"Page: {page} -> Memory: {memory}")
    
    print(f"Total page faults (LRU): {page_faults}")


def page_replacement_menu():
    while True:
        print("\nSelect a page replacement algorithm:")
        print("1. FIFO (First In First Out)")
        print("2. Optimal Page Replacement")
        print("3. LRU (Least Recently Used)")
        print("4. Exit")

        choice = int(input("Enter your choice (1/2/3/4): "))
        
        if choice == 4:
            print("Exiting the program. Goodbye!")
            break
        
        frames = int(input("Enter number of frames: "))
        reference_string = list(map(int, input("Enter reference string (space-separated): ").split()))
        
        algorithms = {
            1: fifo_page_replacement,
            2: optimal_page_replacement,
            3: lru_page_replacement
        }
        
        if choice in algorithms:
            algorithms[choice](frames, reference_string)
        else:
            print("Invalid choice! Please select 1, 2, 3, or 4.")


page_replacement_menu()
