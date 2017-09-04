# encoding: utf-8
#
# Jekyll author page generator.
# http://recursive-design.com/projects/jekyll-plugins/
#
# Version: 0.1.4 (201101061053)
#
# Copyright (c) 2010 Dave Perrett, http://recursive-design.com/
# Licensed under the MIT license (http://www.opensource.org/licenses/mit-license.php)
#
# A generator that creates author pages for jekyll sites.
#
# Included filters :
# - author_links:      Outputs the list of author as comma-separated <a> links.
# - date_to_html_string: Outputs the post.date as formatted html, with hooks for CSS styling.
#
# Available _config.yml settings :
# - author_dir:          The subfolder to build author pages in (default is 'authors').
# - author_title_prefix: The string used before the author name in the page title (default is
#                          'Author: ').

module Jekyll

  # The AuthorIndex class creates a single author page for the specified author.
  class AuthorIndex < Page

    # Initializes a new AuthorIndex.
    #
    #  +base+         is the String path to the <source>.
    #  +author_dir+ is the String path between <source> and the author folder.
    #  +author+     is the author currently being processed.
    def initialize(site, base, author_dir, author)
      @site = site
      @base = base
      @dir = author_dir
      @name = 'index.html'
      puts "author #{author} @dir #{author_dir}"
      self.process(@name)
      # Read the YAML data from the layout page.
      self.read_yaml(File.join(base, '_layouts'), 'author_index.html')
      self.data['author'] = author
      # Set the title for this page.
      title_prefix = site.config['author_title_prefix'] || 'author: '
      self.data['title'] = "#{title_prefix}#{author}"
      # Set the meta-description for this page.
      meta_description_prefix = site.config['author_meta_description_prefix'] || 'author: '
      self.data['description'] = "#{meta_description_prefix}#{author}"
    end

  end


  # The Site class is a built-in Jekyll class with access to global site config information.
  class Site

    # Creates an instance of AuthorIndex for each author page, renders it, and
    # writes the output to a file.
    #
    #  +author_dir+ is the String path to the author folder.
    #  +author+     is the author currently being processed.
    def write_author_index(author_dir, author)
      index = AuthorIndex.new(self, self.source, author_dir, author)
      index.render(self.layouts, site_payload)
      index.write(self.dest)
      # Record the fact that this page has been added, otherwise Site::cleanup will remove it.
      self.pages << index

    end

    # Loops through the list of author pages and processes each one.
    def write_author_indexes
      if self.layouts.key? 'author_index'
        dir = self.config['author_dir'] || 'authors'
        self.posts.docs.each do |post|
          post_authors = post.data["author"]
          if String.try_convert(post_authors)
            post_authors = [post_authors]
          end
          post_authors.each do |author|
            self.write_author_index(File.join(dir, author.downcase.gsub(' ', '-')), author)
          end unless post_authors.nil?
        end
        # Throw an exception if the layout couldn't be found.
      else
        throw "No 'author_index' layout found."
      end
    end

  end


  # Jekyll hook - the generate method is called by jekyll, and generates all of the author pages.
  class GenerateAuthor < Generator
    safe true
    priority :high

    def generate(site)
      site.write_author_indexes
      #puts "site.authors #{site.authors}"
    end

  end


  # Adds some extra filters used during the author creation process.
  module Filters

    # Outputs a list of authors as comma-separated <a> links. This is used
    # to output the author list for each post on a author page.
    #
    #  +author+ is the list of author to format.
    #
    # Returns string
    #
    def author_links(authors)
      dir = @context.registers[:site].config['author_dir'] || "authors"
      if String.try_convert(authors)
        authors = [authors]
      end
      authors = authors.map do |author|
        "<a href='/#{dir}/#{author.downcase.gsub(' ', '-')}/'>#{author}</a>"
      end
      case authors.length
      when 0
        ""
      when 1
        authors[0].to_s
      else
        "#{authors[0...-1].join(', ')}, #{authors[-1]}"
      end
    end

    # Outputs the post.date as formatted html, with hooks for CSS styling.
    #
    #  +date+ is the date object to format as HTML.
    #
    # Returns string
    def date_to_html_string(date)
      result = '<span class="month">' + date.strftime('%b').upcase + '</span> '
      result += date.strftime('<span class="day">%d</span> ')
      result += date.strftime('<span class="year">%Y</span> ')
      result
    end

  end

end
