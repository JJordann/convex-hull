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

    x = ["100", "1000", "10000", "100000"]

    quickhull = groupAverage("quickhull.txt", 10) .|> (x -> x / 1000000)
    liuchen = groupAverage("liuchen.txt", 10) .|> (x -> x / 1000000)

    plot(x, quickhull, label = "Quickhull")
    plot!(x, liuchen, label = "Ordered hull")


    xlabel!("number of points")
    ylabel!("execution time (ms)")

end


