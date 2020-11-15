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

    quickhull = groupAverage("quickhull.txt", 10);


    plot(quickhull);

end


