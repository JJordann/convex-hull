using Plots

function groupAverage(filename, groupSize) 

    groupAverages = []

    open(filename) do file

        while ! eof(file)

            group = []
            for i in 1:groupSize
                if ! eof(file)
                    line = parse(UInt128, readline(file))
                    append!(group, line)
                end
            end

            if length(group) == groupSize
                avg = sum(group) / groupSize
                append!(groupAverages, avg)
            end

        end # while
    end # file

    groupAverages

end # func


function main() 

    x = ["100", "1000", "10000", "100000", "1000000", "10000000"] # 100 .. 10m


    quickhull  = groupAverage("quickhull.txt", 10)     .|> (x -> x / 1000000)
    liuchen    = groupAverage("liuchen.txt", 10)       .|> (x -> x / 1000000)
    alshamrani = groupAverage("alshamrani.txt", 10)    .|> (x -> x / 1000000)
    torch      = groupAverage("torch.txt", 10)         .|> (x -> x / 1000000)
    monotone   = groupAverage("monotoneChain.txt", 10) .|> (x -> x / 1000000)
    chan       = groupAverage("chan.txt", 10)          .|> (x -> x / 1000000)

    plot( x, quickhull,  label = "Quickhull",      lw = 1, marker = 3, legend = :topleft)
    plot!(x, liuchen,    label = "Ordered hull",   lw = 1, marker = 3)
    plot!(x, alshamrani, label = "Alshamrani",     lw = 1, marker = 3)
    plot!(x, torch,      label = "Torch",          lw = 1, marker = 3)
    plot!(x, monotone,   label = "Monotone Chain", lw = 1, marker = 3)
    plot!(x, chan,       label = "Chan",           lw = 1, marker = 3)


    title!("Random circle")
    xlabel!("number of points")
    ylabel!("execution time (ms)")

end


